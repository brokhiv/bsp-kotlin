// Generated from C:/Users/ivobr/JetBrainsProjects/bsp-kotlin/src/main/antlr\RE.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link REParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface REVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Concat}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcat(REParser.ConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Null}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(REParser.NullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Empty}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmpty(REParser.EmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Star}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStar(REParser.StarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Symbol}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbol(REParser.SymbolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Alt}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlt(REParser.AltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(REParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Paren}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen(REParser.ParenContext ctx);
}