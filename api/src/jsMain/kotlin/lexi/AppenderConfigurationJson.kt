@file:JsExport
package lexi

external interface AppenderConfigurationJson {
    val type: String
    val level: String?
    val format: LogFormatterConfigurationJson?
}