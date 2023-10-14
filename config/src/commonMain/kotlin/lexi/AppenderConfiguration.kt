package lexi

import kotlinx.serialization.Serializable

@Serializable
sealed class AppenderConfiguration {

    abstract val level: LevelConfiguration?

    abstract val verbose: Boolean?
}