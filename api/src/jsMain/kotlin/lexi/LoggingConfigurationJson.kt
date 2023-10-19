@file:JsExport
package lexi

external interface LoggingConfigurationJson {
    var level: String?
    var verbose: Boolean?
    var source: Boolean?
    var status: Boolean?
    var appenders: Array<AppenderConfigurationJson>
}