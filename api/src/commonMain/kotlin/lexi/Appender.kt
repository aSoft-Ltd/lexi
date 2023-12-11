@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package lexi

import kotlinx.JsExport
import kotlinx.JsExportIgnore

interface Appender {
    fun append(log: Log)

    fun trace(msg: String, vararg data: Pair<String, Any?>): LogTracer

    fun debug(msg: String, vararg data: Pair<String, Any?>)

    fun info(msg: String, vararg data: Pair<String, Any?>)

    fun warn(msg: String, vararg data: Pair<String, Any?>)

    @JsExportIgnore
    fun error(msg: String, c: Throwable?, vararg data: Pair<String, Any?>)

    @JsExportIgnore
    fun error(msg: String, vararg data: Pair<String, Any?>)

    fun error(c: Throwable?)

    @JsExportIgnore
    fun fatal(msg: String, c: Throwable?, vararg data: Pair<String, Any?>)

    @JsExportIgnore
    fun fatal(msg: String, vararg data: Pair<String, Any?>)

    fun fatal(c: Throwable?)
}