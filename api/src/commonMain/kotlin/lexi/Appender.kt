@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package lexi

import kotlin.js.JsExport

interface Appender {
    fun append(log: Log)

    fun trace(msg: String, vararg data: Pair<String, Any?>): LogTracer

    fun debug(msg: String, vararg data: Pair<String, Any?>)

    fun info(msg: String, vararg data: Pair<String, Any?>)

    fun warn(msg: String, vararg data: Pair<String, Any?>)

    @JsExport.Ignore
    fun error(msg: String, c: Throwable?, vararg data: Pair<String, Any?>)

    @JsExport.Ignore
    fun error(msg: String, vararg data: Pair<String, Any?>)

    fun error(c: Throwable?)

    @JsExport.Ignore
    fun fatal(msg: String, c: Throwable?, vararg data: Pair<String, Any?>)

    @JsExport.Ignore
    fun fatal(msg: String, vararg data: Pair<String, Any?>)

    fun fatal(c: Throwable?)
}