import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InitialFollowTest {
    companion object {
        val mainMRE = MRE.valueOf(mainExample)
        val infAmbiMRE = MRE.valueOf(infAmbi)
    }

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
        assertEquals(expected, mainMRE.iniAS())
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
        expected.forEach { (s, f) -> assertEquals(f, mainMRE.folAS(s), "Iteration: $s\n") }
    }

    @Test
    fun `Symbols of example`() {
        val expected = listOf(
            AcyclicSegment.ASSymbol('a', 4),
            AcyclicSegment.ASSymbol('b', 5),
            AcyclicSegment.ASSymbol('a', 6),
            AcyclicSegment.ASSymbol('a', 7),
            AcyclicSegment.ASSymbol('b', 8),
            AcyclicSegment.ASSymbol('a', 9),
            AcyclicSegment.ASSymbol('b', 11)
        )
        assertEquals(expected, mainMRE.symbols())
    }

    @Test
    fun `Map of Follow sets`() {
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
        assertEquals(expected, mainMRE.folAS())
    }

    @Test
    fun `Initial and Follow of infinitely ambiguous`() {
        val expectedIni = listOf(
            AcyclicSegment(listOf(
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 1)
            ), AcyclicSegment.ASSymbol('a', 2)),
            AcyclicSegment(listOf(
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 1),
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.Empty, 3)
            ), AcyclicSegment.ASSymbol('a', 2)),
            AcyclicSegment(listOf(
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.POpen, 1),
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.Empty, 3),
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 1)
            ), AcyclicSegment.ASSymbol.END)
        )
        assertEquals(expectedIni.toSet(), infAmbiMRE.iniAS().toSet())
        val expectedFol = mapOf(AcyclicSegment.ASSymbol('a', 2) to listOf(
            AcyclicSegment(emptyList(), AcyclicSegment.ASSymbol('a', 2)),
            AcyclicSegment(listOf(
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.Empty, 3)
            ), AcyclicSegment.ASSymbol('a', 2)),
            AcyclicSegment(listOf(
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 1)
            ), AcyclicSegment.ASSymbol.END),
            AcyclicSegment(listOf(
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.Empty, 3),
                AcyclicSegment.ASMeta(AcyclicSegment.Meta.PClose, 1)
            ), AcyclicSegment.ASSymbol.END)
        ))
        assertEquals(expectedFol.mapValues { (_, v) -> v.toSet() }, infAmbiMRE.folAS().mapValues { (_, v) -> v.toSet() })
    }
}