

sealed class RE {
    object Null : RE() {
    }

    object Empty : RE() {
    }

    companion object {
        fun valueOf(text: String) {
            TODO("Connect to Lexer and Parser")
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