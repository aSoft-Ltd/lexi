package lexi

import lexi.internal.LogableImpl

@Deprecated("Use LoggerFactory instead")
inline fun Logable(logger: Logger = Logger("Unknown", emptyList())): Logable = LogableImpl(logger)