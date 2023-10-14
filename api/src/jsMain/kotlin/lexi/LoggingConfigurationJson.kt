@file:JsExport
package lexi

external interface LoggingConfigurationJson {
    val level: String?
    val verbose: Boolean?
    val appenders: Array<AppenderConfigurationJson>
}