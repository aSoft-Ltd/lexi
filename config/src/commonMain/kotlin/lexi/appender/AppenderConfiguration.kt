package lexi.appender

import kotlinx.serialization.Serializable
import lexi.formatter.FormatterConfiguration

@Serializable
sealed class AppenderConfiguration {
    abstract val level: String?
    abstract val formatter: FormatterConfiguration?
}