package lexi

import lexi.internal.WrappedPrintStream

actual val out: PrintStream by lazy { WrappedPrintStream { print(it) } }

actual val err: PrintStream by lazy { WrappedPrintStream { print(it) } }