package lexi.formatter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import lexi.constants.LOG_FORMATTER_SIMPLE_TYPE

@Serializable
@SerialName(LOG_FORMATTER_SIMPLE_TYPE)
class SimpleLogFormatterConfiguration(
    val separator: String? = "=",
    override val source: Boolean? = null,
    override val status: Boolean? = null,
    override val verbose: Boolean? = null
) : FormatterConfiguration()