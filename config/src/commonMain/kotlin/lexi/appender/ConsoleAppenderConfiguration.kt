package lexi.appender

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import lexi.formatter.FormatterConfiguration
import lexi.console.constants.CONSOLE_APPENDER_TYPE

@Serializable
@SerialName(CONSOLE_APPENDER_TYPE)
data class ConsoleAppenderConfiguration(
    override val level: String? = null,
    override val format: FormatterConfiguration? = null
) : AppenderConfiguration()