import java.util.*

data class AcyclicSegment(val metas: List<ASMeta> = emptyList(), val symbol: ASSymbol) {
    data class ASSymbol(val char: Char, val index: Int) {
        constructor(symbol: MSymbol): this(symbol.symbol, symbol.index)
        companion object {
            // Placeholder value for Emptys
            val NONE = ASSymbol('\u0000', -1)
            val END = ASSymbol('⊣', 0)
        }
    }

    enum class Meta {
        POpen,
        PClose,
        Empty
    }

    data class ASMeta(val char: Meta, val index: Int) {

    }
}

fun MRE.iniAS(): List<AcyclicSegment> = when (this) {
    is MAlt -> fst.iniAS() + snd.iniAS()
    is MConcat -> {
        val iniSnd = snd.iniAS()
        fst.iniAS().flatMap { (m, s) ->
            if (s == AcyclicSegment.ASSymbol.NONE)
                // Add iniAS of the second part to Empty expressions
                iniSnd.map { AcyclicSegment(m + it.metas, it.symbol) }
            else listOf(AcyclicSegment(m, s))
        }
    }
    is MRE.MEmpty -> listOf(AcyclicSegment(
        listOf(AcyclicSegment.ASMeta(AcyclicSegment.Meta.Empty, index)),
        AcyclicSegment.ASSymbol.NONE)
    )
    is MRE.MNull -> error("No initial set for Null")
    is MParen -> expr.iniAS().map { (metas, symbol) ->
        AcyclicSegment(
            listOf(AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, index)) + metas +
                // close an empty expression
                (if (symbol == AcyclicSegment.ASSymbol.NONE) listOf(AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, index))
                else emptyList()),
            symbol
        )
    }
    is MPlus -> expr.iniAS()
    is MStar -> expr.iniAS()
    is MSymbol -> listOf(AcyclicSegment(symbol = AcyclicSegment.ASSymbol(this)))
}

fun MRE.folAS(symbol: AcyclicSegment.ASSymbol): List<AcyclicSegment> {
    // Path to symbol
    val parents: Stack<MRE> = Stack<MRE>().apply { push(this@folAS) }
    while (parents.peek() !is MSymbol || AcyclicSegment.ASSymbol(parents.peek() as MSymbol) != symbol) {
        when (val parent = parents.peek()) {
            is MAlt -> parents.push(parent.fst)
            is MConcat -> parents.push(parent.fst)
            is MParen -> parents.push(parent.expr)
            is MPlus -> parents.push(parent.expr)
            is MStar -> parents.push(parent.expr)
            else -> {
                // Find next place to look
                var canContinue = false
                var child = parent
                while (!canContinue) {
                    parents.pop()
                    when (val grandParent = parents.peek()) {
                        is MAlt -> if (grandParent.fst == child) {
                            parents.push(grandParent.snd)
                            canContinue = true
                        } else child = grandParent
                        is MConcat -> if (grandParent.fst == child) {
                            parents.push(grandParent.snd)
                            canContinue = true
                        } else child = grandParent
                        is MParen -> child = grandParent
                        is MPlus -> child = grandParent
                        is MStar -> child = grandParent
                        else -> error("Invalid parent of $child")
                    }
                }
            }
        }
    }
    val follow = mutableListOf<AcyclicSegment>()
    val metas = mutableListOf<AcyclicSegment.ASMeta>()

    // Keep track of the last child
    var lastChild = parents.pop()
    while (!parents.empty()) {
        val parent = parents.pop()
        when (parent) {
            is MAlt -> lastChild = parent
            is MConcat -> if (parent.fst == lastChild)
                    return follow + parent.snd.iniAS().map { AcyclicSegment(metas + it.metas, it.symbol) }
                else lastChild = parent
            is MParen -> metas += AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, parent.index)
            is MPlus -> follow.addAll(parent.expr.iniAS().map { AcyclicSegment(metas + it.metas, it.symbol) } )
            is MStar -> follow.addAll(parent.expr.iniAS().map { AcyclicSegment(metas + it.metas, it.symbol) } )
            else -> error("Invalid parent $parent")
        }
        lastChild = parent
    }

    return follow + listOf(AcyclicSegment(metas, AcyclicSegment.ASSymbol.END))
}