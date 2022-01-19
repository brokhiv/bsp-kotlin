import org.junit.jupiter.api.Test

class DFTTest {
    // Will always succeed if it runs, make sure to check the result manually
    @Test
    fun `Create DFT from mainMRE, check manually`() {
        println(constructDFT(InitialFollowTest.mainMRE))
    }

    @Test
    fun `Create DFT from infAmbi, check manually`() {
        println(constructDFT(InitialFollowTest.infAmbiMRE))
    }

    @Test
    fun `Create DAG from mainMRE(aab), check manually`() {
        println(constructDFT(InitialFollowTest.mainMRE).dag("aab"))
    }

    @Test
    fun `Create DAG from infAmbi(a), check manually`() {
        println(constructDFT(InitialFollowTest.infAmbiMRE).dag("a"))
    }

    @Test
    fun `Create LSTs from mainMRE(aab), check manually`() {
        println(constructDFT(InitialFollowTest.mainMRE).lsts("aab"))
    }
}