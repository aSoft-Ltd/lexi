package lexi.appender

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import lexi.formatter.FormatterConfiguration
import lexi.file.constants.FILE_APPENDER_TYPE

@Serializable
@SerialName(FILE_APPENDER_TYPE)
class FileAppenderConfiguration(
    val directory: String,
    override val level: String? = null,
    override val format: FormatterConfiguration? = null
) : AppenderConfiguration()