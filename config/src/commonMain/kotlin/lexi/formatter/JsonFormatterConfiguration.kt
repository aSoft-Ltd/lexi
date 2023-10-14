package lexi.formatter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import lexi.constants.LOG_FORMATTER_JSON_TYPE

@Serializable
@SerialName(LOG_FORMATTER_JSON_TYPE)
class JsonFormatterConfiguration(
    val tab: String? = "  ",
    override val source: Boolean? = null,
    override val status: Boolean? = null,
    override val verbose: Boolean? = null
) : FormatterConfiguration()