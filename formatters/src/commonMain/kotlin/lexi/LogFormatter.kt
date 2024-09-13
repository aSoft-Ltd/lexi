package lexi

sealed interface LogFormatter {
    fun format(log: Log) : String
}