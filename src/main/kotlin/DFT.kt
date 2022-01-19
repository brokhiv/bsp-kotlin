// State with number and contents (I in the paper)
data class State(val id: Int, val contents: Set<AcyclicSegment.ASSymbol>) {
    // Simplified toString that just displays the name
    override fun toString() = if (this == START) "START" else "q$id"
}
val START = State(-1, setOf(AcyclicSegment.ASSymbol.START))
typealias DFTTriple = Triple<AcyclicSegment.ASSymbol, List<AcyclicSegment.ASMeta>, AcyclicSegment.ASSymbol>
typealias DFTOutput = List<DFTTriple>
typealias SlicedDFTTriple = Pair<DFTTriple, Int>

data class Graph<T>(val vertices: List<T>, val edges: List<Pair<T,T>>) {

}

data class DFT(
    val states: List<State>,
    val start: State = START,
    val transitions: Map<Pair<State, Symbol>, State>,
    val outputs: Map<Pair<State, Symbol>, DFTOutput>,
    val final: List<State>
) {

    override fun toString(): String =
"""
DFT(
    states=${states.joinToString(",\n        ") { it.toString() + if (it == START) "" else it.contents.joinToString(prefix = "[", postfix = "]") } },
    start=$start,
    transitions=${transitions.entries.joinToString(",\n        ") { "${it.key.first} --${it.key.second}-> ${it.value}" } },
    outputs=${outputs.entries.joinToString(",\n        ") { "(${it.key.first}, ${it.key.second}): ${it.value.joinToString(",\n            ") { "<${it.first}, ${it.second.let{if (it.isEmpty()) 'ε' else it.joinToString(" ")}}, ${it.third}>" } }" } },
    final=${final.joinToString(",\n        ")}
)
""".trimIndent()

    private fun transition(a: Symbol, state: State = start): Pair<DFTOutput, State> {
        val output = outputs[state to a]!!
        val state = transitions[state to a]!!
        return output to state
    }

    fun dag(x: String): Graph<SlicedDFTTriple> {
        // Make all slices
        var (o0, q) = transition(Symbol.START)
        val slices = mutableListOf(o0)
        for (a in x) {
            val (o, qNext) = transition(Symbol(a), q)
            slices.add(o)
            q = qNext
        }

        // Vertices is all slices combined, with the index of the slice coupled to the vertex
        val vertices = slices.foldIndexed(emptyList<SlicedDFTTriple>()) { i, acc, slice -> acc + slice.map { it to i } }

        // Make all edges by finding the matching vertex pairs
        val edges = vertices.flatMap { (v1, i1) -> vertices
            .filter { (v2, i2) -> i2 == i1 + 1 && v2.first == v1.third }
            .map { (v2, i2) -> SlicedDFTTriple(v1, i1) to SlicedDFTTriple(v2, i2) }
        }

        return Graph(vertices, edges)
    }

    fun lsts(x: String): List<List<AcyclicSegment>> {
        val (dagVertices, dagEdges) = dag(x)
        // Create all possible first steps of the paths
        var paths = dagVertices
            .filter { it.second == 0 }
            .map { it.first
                .let { listOf(AcyclicSegment(it.second, it.third)) }
            }

        // Extend the path up to the next slice, keeping only those that continue to the end
        for (i in x.indices) {
            val steps = dagEdges.filter { it.first.second == i }
            paths = paths.flatMap { es -> steps
                .filter { it.first.first.let { AcyclicSegment(it.second, it.third) } == es.last() }
                .map { it.second.first
                    .let { es + AcyclicSegment(it.second, it.third) }
                }
            }
        }

        // Get rid of any non-recognizing paths (i.e. the ones without a ⊣ at the end)
        return paths.filter { it.last().symbol == AcyclicSegment.ASSymbol.END }
    }

}

/**
 * Mutable version of the DFT, used to build it up.
 */
data class MutableDFT(
    val states: MutableList<State> = mutableListOf(),
    var start: State = START,
    val transitions: MutableMap<Pair<State, Symbol>, State> = mutableMapOf(),
    val outputs: MutableMap<Pair<State, Symbol>, DFTOutput> = mutableMapOf(),
    val final: MutableList<State> = mutableListOf()
) {
    fun toDFT() = DFT(states.toList(), start, transitions.toMap(), outputs.toMap(), final.toList())
}

/**
 * Analogous to the buildList function, builds a DFT from the given actions.
 */
fun buildDFT(builderAction: MutableDFT.() -> Unit) = MutableDFT().apply(builderAction).toDFT()

/**
 * Constructs the DFT according to Algorithm 1 in the paper.
 */
fun constructDFT(iniAS: List<AcyclicSegment>, folAS: Map<AcyclicSegment.ASSymbol, List<AcyclicSegment>>) = buildDFT {
    // (Subset of) the alphabet of the RE, consisting of all characters
    val alphabet = folAS.keys.map { it.char }.distinct()
    // Counter to assign each state distinctly
    var nextState = 1
    // TS variable in the paper
    var ts: DFTOutput
    /* ---------------------------------------------------------------------------------------------- */

    states.add(START)

    /* ---------------------------------------------------------------------------------------------- */

    val untagged = mutableSetOf<State>()
    val q0 = State(0, iniAS.map { it.symbol }.toSet())
    untagged.add(q0)
    ts = iniAS.map { Triple(AcyclicSegment.ASSymbol.START, it.metas, it.symbol) }
    states.add(q0)
    transitions[START to Symbol.START] = q0
    outputs[START to Symbol.START] = ts

    /* ---------------------------------------------------------------------------------------------- */

    while (states.intersect(untagged).isNotEmpty()) {
        // Take a state q from untagged and remove it
        val q: State = states.intersect(untagged).first().also { untagged.remove(it) }
        for (a in alphabet) {
            val newContents = mutableSetOf<AcyclicSegment.ASSymbol>()
            ts = mutableListOf()
            for (b in q.contents.filter { it.char == a }) {
                newContents += folAS[b]!!.map { it.symbol }.distinct()
                ts = ts + folAS[b]!!.map { Triple(b, it.metas, it.symbol) }
            }

            if (newContents.isNotEmpty()) {
                if (newContents !in states.map { it.contents }) {
                    val qPrime = State(nextState++, newContents)
                    states.add(qPrime)
                    untagged.add(qPrime)
                }
                // Find the state we discovered, be it newly added or already present
                transitions[q to Symbol(a)] = states.first { it.contents == newContents }
                outputs[q to Symbol(a)] = ts
            }
        }
    }

    /* ---------------------------------------------------------------------------------------------- */

    final.addAll(states.filter { AcyclicSegment.ASSymbol.END in it.contents })
}

/**
 * Variant of DFT construction to directly call it on an MRE.
 */
fun constructDFT(mre: MRE) = constructDFT(mre.iniAS(), mre.folAS())