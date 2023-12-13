package lexi

import lexi.internal.WrappedPrintStream
import lexi.internal.process

actual val out: PrintStream by lazy {
    WrappedPrintStream {
        if (process?.versions !== undefined) {
            process.stdout.write(it)
        } else {
            console.log(it)
        }
    }
}

actual val err: PrintStream by lazy {
    WrappedPrintStream {
        if (process?.versions !== undefined) {
            process.stderr.write(it)
        } else {
            console.error(it)
        }
    }
}