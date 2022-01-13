// Generated from C:/Users/ivobr/JetBrainsProjects/bsp-kotlin/src/main/antlr\RE.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class REParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, SYMBOL=8, SPACE=9;
	public static final int
		RULE_regex = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"regex"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'0'", "'1'", "'*'", "'+'", "'|'", "'('", "')'", null, "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "SYMBOL", "SPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RE.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public REParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class RegexContext extends ParserRuleContext {
		public RegexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regex; }
	 
		public RegexContext() { }
		public void copyFrom(RegexContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConcatContext extends RegexContext {
		public List<RegexContext> regex() {
			return getRuleContexts(RegexContext.class);
		}
		public RegexContext regex(int i) {
			return getRuleContext(RegexContext.class,i);
		}
		public ConcatContext(RegexContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).enterConcat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).exitConcat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof REVisitor ) return ((REVisitor<? extends T>)visitor).visitConcat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullContext extends RegexContext {
		public NullContext(RegexContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).exitNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof REVisitor ) return ((REVisitor<? extends T>)visitor).visitNull(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyContext extends RegexContext {
		public EmptyContext(RegexContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).enterEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).exitEmpty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof REVisitor ) return ((REVisitor<? extends T>)visitor).visitEmpty(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StarContext extends RegexContext {
		public RegexContext regex() {
			return getRuleContext(RegexContext.class,0);
		}
		public StarContext(RegexContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).enterStar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).exitStar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof REVisitor ) return ((REVisitor<? extends T>)visitor).visitStar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SymbolContext extends RegexContext {
		public TerminalNode SYMBOL() { return getToken(REParser.SYMBOL, 0); }
		public SymbolContext(RegexContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).enterSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).exitSymbol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof REVisitor ) return ((REVisitor<? extends T>)visitor).visitSymbol(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AltContext extends RegexContext {
		public List<RegexContext> regex() {
			return getRuleContexts(RegexContext.class);
		}
		public RegexContext regex(int i) {
			return getRuleContext(RegexContext.class,i);
		}
		public AltContext(RegexContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).enterAlt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).exitAlt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof REVisitor ) return ((REVisitor<? extends T>)visitor).visitAlt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusContext extends RegexContext {
		public RegexContext regex() {
			return getRuleContext(RegexContext.class,0);
		}
		public PlusContext(RegexContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).enterPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).exitPlus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof REVisitor ) return ((REVisitor<? extends T>)visitor).visitPlus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenContext extends RegexContext {
		public RegexContext regex() {
			return getRuleContext(RegexContext.class,0);
		}
		public ParenContext(RegexContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).enterParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof REListener ) ((REListener)listener).exitParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof REVisitor ) return ((REVisitor<? extends T>)visitor).visitParen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegexContext regex() throws RecognitionException {
		return regex(0);
	}

	private RegexContext regex(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RegexContext _localctx = new RegexContext(_ctx, _parentState);
		RegexContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_regex, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				_localctx = new NullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(3);
				match(T__0);
				}
				break;
			case T__1:
				{
				_localctx = new EmptyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(4);
				match(T__1);
				}
				break;
			case SYMBOL:
				{
				_localctx = new SymbolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(5);
				match(SYMBOL);
				}
				break;
			case T__5:
				{
				_localctx = new ParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(6);
				match(T__5);
				setState(7);
				regex(0);
				setState(8);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(23);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(21);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new ConcatContext(new RegexContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_regex);
						setState(12);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(13);
						regex(4);
						}
						break;
					case 2:
						{
						_localctx = new AltContext(new RegexContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_regex);
						setState(14);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(15);
						match(T__4);
						setState(16);
						regex(3);
						}
						break;
					case 3:
						{
						_localctx = new StarContext(new RegexContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_regex);
						setState(17);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(18);
						match(T__2);
						}
						break;
					case 4:
						{
						_localctx = new PlusContext(new RegexContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_regex);
						setState(19);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(20);
						match(T__3);
						}
						break;
					}
					} 
				}
				setState(25);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return regex_sempred((RegexContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean regex_sempred(RegexContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13\35\4\2\t\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\r\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\7\2\30\n\2\f\2\16\2\33\13\2\3\2\2\3\2\3\2\2\2\2\"\2\f\3\2\2\2\4\5"+
		"\b\2\1\2\5\r\7\3\2\2\6\r\7\4\2\2\7\r\7\n\2\2\b\t\7\b\2\2\t\n\5\2\2\2\n"+
		"\13\7\t\2\2\13\r\3\2\2\2\f\4\3\2\2\2\f\6\3\2\2\2\f\7\3\2\2\2\f\b\3\2\2"+
		"\2\r\31\3\2\2\2\16\17\f\5\2\2\17\30\5\2\2\6\20\21\f\4\2\2\21\22\7\7\2"+
		"\2\22\30\5\2\2\5\23\24\f\7\2\2\24\30\7\5\2\2\25\26\f\6\2\2\26\30\7\6\2"+
		"\2\27\16\3\2\2\2\27\20\3\2\2\2\27\23\3\2\2\2\27\25\3\2\2\2\30\33\3\2\2"+
		"\2\31\27\3\2\2\2\31\32\3\2\2\2\32\3\3\2\2\2\33\31\3\2\2\2\5\f\27\31";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}