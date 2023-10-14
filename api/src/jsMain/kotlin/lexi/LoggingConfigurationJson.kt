@file:JsExport
package lexi

external interface LoggingConfigurationJson {
    val level: String?
    val verbose: Boolean?
    val source: Boolean?
    val status: Boolean?
    val appenders: Array<AppenderConfigurationJson>
}