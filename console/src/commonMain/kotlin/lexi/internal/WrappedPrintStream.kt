package lexi.internal

import lexi.PrintStream

internal class WrappedPrintStream(val printer: (Any?) -> Unit) : PrintStream {
    override fun print(msg: String?) = printer(msg)
    override fun println(msg: String?) = printer("${msg ?: ""}\n")
}