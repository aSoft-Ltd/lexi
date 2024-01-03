@file:JsExport

package lexi.builder

import kotlinx.JsExport

open class LoggingFormatterConfigurationBuilder(
    internal var source: Boolean? = null,
    internal var status: Boolean? = null,
    internal var verbose: Boolean? = null,
) {
    internal var type: String = "simple"
    internal var tab: String? = null
    internal var separator: String? = null
    fun json(tab: String = "  ") {
        type = "json"
        this.tab = tab
    }

    fun simple(separator: String = "=") {
        type = "simple"
        this.separator = separator
    }

    fun source(value: Boolean) {
        source = value
    }

    fun status(value: Boolean) {
        status = value
    }

    fun verbose(value: Boolean) {
        verbose = value
    }
}