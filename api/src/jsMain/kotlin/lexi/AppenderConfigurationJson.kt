@file:JsExport
package lexi

external interface AppenderConfigurationJson {
    var type: String
    var level: String?
    var format: LogFormatterConfigurationJson?
}