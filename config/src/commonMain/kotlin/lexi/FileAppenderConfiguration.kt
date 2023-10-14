package lexi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("file")
class FileAppenderConfiguration(
    val directory: String,
    override val level: LevelConfiguration? = null,
    override var verbose: Boolean? = null
) : AppenderConfiguration()