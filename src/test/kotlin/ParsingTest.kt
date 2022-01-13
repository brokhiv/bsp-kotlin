import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

const val text = "(((a)+ | ba | aba)+ | 1) b"

class REParsingTest {
    @Test
    fun `AST of example`() {
        val expected = Concat(
            Alt(
                Plus(
                    Alt(
                        Alt(
                            Plus(Symbol('a')),
                            Concat(Symbol('b'), Symbol('a'))
                        ),
                        Concat(
                            Concat(Symbol('a'), Symbol('b')),
                            Symbol('a')
                        )
                    )
                ),
                RE.Empty
            ),
            Symbol('b')
        )
        assertEquals(RE.valueOf(text), expected)
    }

    @Test
    fun `MAST of example`() {
        val expected = MConcat(
            MParen(
                MAlt(
                    MParen(
                        MPlus(
                            MAlt(
                                MAlt(
                                    MParen(
                                        MPlus(
                                            MSymbol('a', 4)
                                        ),
                                        3
                                    ),
                                    MConcat(
                                        MSymbol('b', 5),
                                        MSymbol('a', 6)
                                    )
                                ),
                                MConcat(
                                    MConcat(
                                        MSymbol('a', 7),
                                        MSymbol('b', 8)
                                    ),
                                    MSymbol('a', 9)
                                )
                            )
                        ),
                        2
                    ),
                    MRE.MEmpty(10)
                ),
                1
            ),
            MSymbol('b', 11)
        )
        assertEquals(MRE.valueOf(text), expected)
    }
}