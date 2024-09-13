package lexi

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class LoggingConfiguration(
    internal val level: String? = null,
    internal val source: Boolean? = null,
    internal val status: Boolean? = null,
    internal val verbose: Boolean? = null,
    internal val appender: List<Map<String, String>> = emptyList()
) {
    @Transient
    internal val appenders = mutableMapOf<String, (params: Map<String, String>) -> Appender>()

    internal val solid = mutableListOf<() -> Appender>()

    fun register(block: () -> Appender) = solid.add(block)

    fun register(name: String, block: (params: Map<String, String>) -> Appender) {
        appenders[name] = block
    }
}