@file:JsExport
package lexi

external interface LogFormatterConfigurationJson {
    val type: String
    val source: Boolean?
    val status: Boolean?
    val verbose: Boolean?
}