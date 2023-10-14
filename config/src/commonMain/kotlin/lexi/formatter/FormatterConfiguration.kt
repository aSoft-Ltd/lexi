package lexi.formatter

import kotlinx.serialization.Serializable

@Serializable
sealed class FormatterConfiguration {
    abstract val source: Boolean?
    abstract val status: Boolean?
    abstract val verbose: Boolean?
}