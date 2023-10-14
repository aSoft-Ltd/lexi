package lexi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("console")
data class ConsoleAppenderConfiguration(
    override val level: LevelConfiguration? = null,
    override var verbose: Boolean? = null
) : AppenderConfiguration()