package lexi.internal

import lexi.Appender
import lexi.Log
import lexi.LogLevel
import lexi.LogStatus
import lexi.LogTracer

abstract class AbstractAppender : Appender {

    override fun trace(msg: String, vararg data: Pair<String, Any?>): LogTracer {
        append(Log(level = LogLevel.TRACE, msg, status = LogStatus.Started, source = "Unset", data.toMap()))
        return LogTracer(msg, this)
    }

    override fun debug(msg: String, vararg data: Pair<String, Any?>) {
        append(Log(level = LogLevel.DEBUG, msg, status = null, "Unset", data.toMap()))
    }

    override fun info(msg: String, vararg data: Pair<String, Any?>) {
        append(Log(level = LogLevel.INFO, msg, status = null, "Unset", data.toMap()))
    }

    override fun warn(msg: String, vararg data: Pair<String, Any?>) {
        append(Log(level = LogLevel.WARNING, msg, status = null, "Unset", data.toMap()))
    }

    override fun error(msg: String, c: Throwable?, vararg data: Pair<String, Any?>) {
        val md = buildMap {
            putAll(data)
            if (c != null) put("cause", c.message)
        }
        append(Log(level = LogLevel.ERROR, msg, status = null, "Unset", md))
    }

    override fun error(msg: String, vararg data: Pair<String, Any?>) = error(msg, null, *data)

    override fun error(c: Throwable?) = error(msg = c?.message ?: "Unknown error", c?.cause)

    override fun fatal(msg: String, c: Throwable?, vararg data: Pair<String, Any?>) {
        val md = buildMap {
            putAll(data)
            if (c != null) put("cause", c.message)
        }
        append(Log(level = LogLevel.FATAL, msg, status = null, "Unset", md))
    }

    override fun fatal(msg: String, vararg data: Pair<String, Any?>) = fatal(msg, null, *data)

    override fun fatal(c: Throwable?) = fatal(msg = c?.message ?: "Unknown fatal error", c?.cause)
}