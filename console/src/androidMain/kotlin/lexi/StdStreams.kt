package lexi

import android.util.Log
import lexi.internal.WrappedPrintStream

actual val out: PrintStream by lazy { WrappedPrintStream { Log.i("stdout", "$it") } }

actual val err: PrintStream by lazy { WrappedPrintStream { Log.e("stderr", "$it") } }