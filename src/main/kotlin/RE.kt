import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode

sealed class RE {
    object Null : RE() {
    }

    object Empty : RE() {
    }

    companion object {
        fun valueOf(text: String): RE {
            val visitor = object: REVisitor<RE> {
                override fun visit(tree: ParseTree?): RE {
                    return when (tree) {
                        is REParser.AltContext? -> visitAlt(tree)
                        is REParser.ConcatContext? -> visitConcat(tree)
                        is REParser.EmptyContext? -> visitEmpty(tree)
                        is REParser.NullContext? -> visitNull(tree)
                        is REParser.ParenContext? -> visitParen(tree)
                        is REParser.PlusContext? -> visitPlus(tree)
                        is REParser.StarContext? -> visitStar(tree)
                        is REParser.SymbolContext? -> visitSymbol(tree)
                        else -> error("Parsing failed")
                    }
                }

                override fun visitChildren(node: RuleNode?): RE {
                    TODO("Not yet implemented")
                }

                override fun visitTerminal(node: TerminalNode?): RE {
                    TODO("Not yet implemented")
                }

                override fun visitErrorNode(node: ErrorNode?): RE {
                    error(node?.text ?: "")
                }

                override fun visitConcat(ctx: REParser.ConcatContext?): RE {
                    return Concat(visit(ctx!!.regex(0)), visit(ctx.regex(1)))
                }

                override fun visitNull(ctx: REParser.NullContext?): RE = Null

                override fun visitEmpty(ctx: REParser.EmptyContext?): RE = Empty

                override fun visitStar(ctx: REParser.StarContext?): RE {
                    return Star(visit(ctx!!.regex()))
                }

                override fun visitSymbol(ctx: REParser.SymbolContext?): RE = Symbol(ctx!!.SYMBOL().text.single())

                override fun visitAlt(ctx: REParser.AltContext?): RE {
                    return Alt(visit(ctx!!.regex(0)), visit(ctx.regex(1)))
                }

                override fun visitPlus(ctx: REParser.PlusContext?): RE {
                    return Plus(visit(ctx!!.regex()))
                }

                override fun visitParen(ctx: REParser.ParenContext?): RE {
                    return visit(ctx!!.regex())
                }
            }

            return visitor.visit(REParser(CommonTokenStream(RELexer(CharStreams.fromString(text)))).regex())
        }
    }
}

data class Symbol(val symbol: Char) : RE() {
    companion object {
        val START = Symbol('⊢')
        val END = Symbol('⊣')
    }

    override fun toString() = symbol.toString()
}

data class Star(val expr: RE) : RE() {
}

data class Plus(val expr: RE) : RE() {
}

data class Concat(val fst: RE, val snd: RE) : RE() {
}

data class Alt(val fst: RE, val snd: RE) : RE() {
}

sealed class MRE(open val index: Int? = null): RE() {
    data class MNull(override val index: Int): MRE(index)
    data class MEmpty(override val index: Int): MRE(index)

    companion object {
        fun valueOf(text: String): MRE {
            val visitor = object: REVisitor<MRE> {
                // next index to be used, will be incremented every time it is issued
                var nextIndex = 1

                override fun visit(tree: ParseTree?): MRE {
                    return when (tree) {
                        is REParser.AltContext? -> visitAlt(tree)
                        is REParser.ConcatContext? -> visitConcat(tree)
                        is REParser.EmptyContext? -> visitEmpty(tree)
                        is REParser.NullContext? -> visitNull(tree)
                        is REParser.ParenContext? -> visitParen(tree)
                        is REParser.PlusContext? -> visitPlus(tree)
                        is REParser.StarContext? -> visitStar(tree)
                        is REParser.SymbolContext? -> visitSymbol(tree)
                        else -> error("Parsing failed")
                    }
                }

                override fun visitChildren(node: RuleNode?): MRE {
                    TODO("Not yet implemented")
                }

                override fun visitTerminal(node: TerminalNode?): MRE {
                    TODO("Not yet implemented")
                }

                override fun visitErrorNode(node: ErrorNode?): MRE {
                    error(node?.text ?: "")
                }

                override fun visitConcat(ctx: REParser.ConcatContext?): MRE {
                    val fst = visit(ctx!!.regex(0))
                    val snd = visit(ctx.regex(1))
                    return MConcat(fst, snd)
                }

                override fun visitNull(ctx: REParser.NullContext?): MRE = MNull(nextIndex++)

                override fun visitEmpty(ctx: REParser.EmptyContext?): MRE = MEmpty(nextIndex++)

                override fun visitStar(ctx: REParser.StarContext?): MRE {
                    val child = visit(ctx!!.getChild(0))
                    return if (child is MParen) MParen(MStar(child.expr), child.index) else MStar(child)
                }

                override fun visitSymbol(ctx: REParser.SymbolContext?): MRE = MSymbol(ctx!!.text.single(), nextIndex++)

                override fun visitAlt(ctx: REParser.AltContext?): MRE {
                    val fst = visit(ctx!!.regex(0))
                    val snd = visit(ctx.regex(1))
                    return MAlt(fst, snd)
                }

                override fun visitPlus(ctx: REParser.PlusContext?): MRE {
                    val child = visit(ctx!!.getChild(0))
                    return if (child is MParen) MParen(MPlus(child.expr), child.index) else MPlus(child)
                }

                override fun visitParen(ctx: REParser.ParenContext?): MRE {
                    // index of parentheses goes before indices of children
                    val index = nextIndex++
                    return MParen(visit(ctx!!.regex()), index)
                }

            }
            return visitor.visit(REParser(CommonTokenStream(RELexer(CharStreams.fromString(text)))).regex())
        }
    }
}

data class MSymbol(val symbol: Char, override val index: Int): MRE(index) {
}

data class MStar(val expr: MRE): MRE() {
}

data class MPlus(val expr: MRE): MRE() {
}

data class MParen(val expr: MRE, override val index: Int): MRE(index) {
}

data class MConcat(val fst: MRE, val snd: MRE): MRE() {
}

data class MAlt(val fst: MRE, val snd: MRE): MRE() {
}