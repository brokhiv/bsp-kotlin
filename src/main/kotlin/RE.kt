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
                    return Concat(visit(ctx!!.getChild(0)), visit(ctx.getChild(1)))
                }

                override fun visitNull(ctx: REParser.NullContext?): RE = Null

                override fun visitEmpty(ctx: REParser.EmptyContext?): RE = Empty

                override fun visitStar(ctx: REParser.StarContext?): RE {
                    return Star(visit(ctx!!.getChild(0)))
                }

                override fun visitSymbol(ctx: REParser.SymbolContext?): RE = Symbol(ctx!!.SYMBOL().text.single())

                override fun visitAlt(ctx: REParser.AltContext?): RE {
                    return Alt(visit(ctx!!.getChild(0)), visit(ctx.getChild(1)))
                }

                override fun visitPlus(ctx: REParser.PlusContext?): RE {
                    return Plus(visit(ctx!!.getChild(0)))
                }

                override fun visitParen(ctx: REParser.ParenContext?): RE {
                    return visit(ctx!!.getChild(0))
                }
            }

            return visitor.visit(REParser(CommonTokenStream(RELexer(CharStreams.fromString(text)))).regex())
        }
    }
}

data class Symbol(val symbol: Char) : RE() {
}

data class Star(val expr: RE) : RE() {
}

data class Plus(val expr: RE) : RE() {
}

data class Concat(val fst: RE, val snd: RE) : RE() {
}

data class Alt(val a: RE, val b: RE) : RE() {
}