// Generated from /Users/ivobroekhof/JetBrainsProjects/bsp-kotlin/src/main/antlr/RE.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link REParser}.
 */
public interface REListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Concat}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void enterConcat(REParser.ConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Concat}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void exitConcat(REParser.ConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Null}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void enterNull(REParser.NullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Null}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void exitNull(REParser.NullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Empty}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void enterEmpty(REParser.EmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Empty}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void exitEmpty(REParser.EmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Star}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void enterStar(REParser.StarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Star}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void exitStar(REParser.StarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Symbol}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(REParser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Symbol}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(REParser.SymbolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Alt}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void enterAlt(REParser.AltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Alt}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void exitAlt(REParser.AltContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void enterPlus(REParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void exitPlus(REParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Paren}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void enterParen(REParser.ParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Paren}
	 * labeled alternative in {@link REParser#regex}.
	 * @param ctx the parse tree
	 */
	void exitParen(REParser.ParenContext ctx);
}