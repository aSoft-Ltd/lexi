@file:JsExport

package lexi.builder

import kotlinx.JsExport
import lexi.formatter.FormatterConfiguration
import kotlin.jvm.JvmOverloads

sealed class AppenderConfigurationBuilder(
    internal var level: String? = null,
    internal var enabled: Boolean? = null
) {
    fun level(value: String) {
        level = value
    }

    fun enabled(state: Boolean) {
        enabled = state
    }

    val format = LoggingFormatterConfigurationBuilder()
}