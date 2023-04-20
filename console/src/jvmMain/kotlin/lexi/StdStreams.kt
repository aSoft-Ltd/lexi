package lexi

import lexi.internal.WrappedPrintStream

actual val out: PrintStream by lazy { WrappedPrintStream { System.out.print(it) } }

actual val err: PrintStream by lazy { WrappedPrintStream { System.err.print(it) } }