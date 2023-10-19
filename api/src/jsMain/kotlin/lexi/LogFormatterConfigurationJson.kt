@file:JsExport
package lexi

external interface LogFormatterConfigurationJson {
    var type: String
    var source: Boolean?
    var status: Boolean?
    var verbose: Boolean?
}