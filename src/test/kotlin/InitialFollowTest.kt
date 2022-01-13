import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InitialFollowTest {
    val mre = MRE.valueOf(text)

    @Test
    fun `Initial set of example`() {
        val expected = listOf(
            AcyclicSegment(listOf(
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 1),
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 2),
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 3)
            ), AcyclicSegment.ASSymbol('a', 4)),
            AcyclicSegment(listOf(
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 1),
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 2)
            ), AcyclicSegment.ASSymbol('b', 5)),
            AcyclicSegment(listOf(
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 1),
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 2)
            ), AcyclicSegment.ASSymbol('a', 7)),
            AcyclicSegment(listOf(
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 1),
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.Empty, 10),
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 1)
            ), AcyclicSegment.ASSymbol('b', 11))
        )
        assertEquals(expected, mre.iniAS())
    }

    @Test
    fun `Follow sets of example`() {
        val expected = mapOf(
            AcyclicSegment.ASSymbol('a', 4) to listOf(
                AcyclicSegment(symbol = AcyclicSegment.ASSymbol('a', 4)),
                AcyclicSegment(listOf(
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 3),
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 3)
                ), AcyclicSegment.ASSymbol('a', 4)),
                AcyclicSegment(listOf(
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 3)
                ), AcyclicSegment.ASSymbol('b', 5)),
                AcyclicSegment(listOf(
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 3)
                ), AcyclicSegment.ASSymbol('a', 7)),
                AcyclicSegment(listOf(
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 3),
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 2),
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 1)
                ), AcyclicSegment.ASSymbol('b', 11))
            ),
            AcyclicSegment.ASSymbol('b', 5) to listOf(
                AcyclicSegment(symbol = AcyclicSegment.ASSymbol('a', 6))
            ),
            AcyclicSegment.ASSymbol('a', 6) to listOf(
                AcyclicSegment(listOf(
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 3)
                ), AcyclicSegment.ASSymbol('a', 4)),
                AcyclicSegment(symbol = AcyclicSegment.ASSymbol('b', 5)),
                AcyclicSegment(symbol = AcyclicSegment.ASSymbol('a', 7)),
                AcyclicSegment(listOf(
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 2),
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 1)
                ), AcyclicSegment.ASSymbol('b', 11))
            ),
            AcyclicSegment.ASSymbol('a', 7) to listOf(
                AcyclicSegment(symbol = AcyclicSegment.ASSymbol('b', 8))
            ),
            AcyclicSegment.ASSymbol('b', 8) to listOf(
                AcyclicSegment(symbol = AcyclicSegment.ASSymbol('a', 9))
            ),
            AcyclicSegment.ASSymbol('a', 9) to listOf(
                AcyclicSegment(listOf(
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 3)
                ), AcyclicSegment.ASSymbol('a', 4)),
                AcyclicSegment(symbol = AcyclicSegment.ASSymbol('b', 5)),
                AcyclicSegment(symbol = AcyclicSegment.ASSymbol('a', 7)),
                AcyclicSegment(listOf(
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 2),
                    AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 1)
                ), AcyclicSegment.ASSymbol('b', 11))
            ),
            AcyclicSegment.ASSymbol('b', 11) to listOf(
                AcyclicSegment(symbol = AcyclicSegment.ASSymbol.END)
            )
        )
        expected.forEach { (s, f) -> assertEquals(f, mre.folAS(s), "Iteration: $s\n") }
    }
}