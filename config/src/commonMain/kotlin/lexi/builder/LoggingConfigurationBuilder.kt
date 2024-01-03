@file:JsExport

package lexi.builder

import kotlinx.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

class LoggingConfigurationBuilder @JvmOverloads constructor(
    internal var level: String = "debug"
) {
    fun level(value: String) {
        level = value
    }

    @JvmField
    val format = LoggingFormatterConfigurationBuilder()

    @JvmField
    val console = ConsoleAppenderConfigurationBuilder()

    @JvmField
    val file = FileAppenderConfigurationBuilder()
}