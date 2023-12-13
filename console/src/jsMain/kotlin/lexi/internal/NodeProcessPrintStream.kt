package lexi.internal

internal external interface NodeProcessPrintStream {
    fun write(msg: Any?)
}

internal external interface Process {
    val stderr: NodeProcessPrintStream
    val stdout: NodeProcessPrintStream
    val versions: Any?
}

internal external val process: Process?