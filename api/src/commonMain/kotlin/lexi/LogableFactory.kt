package lexi

import lexi.internal.LogableImpl

inline fun Logable(logger: Logger = Logger()): Logable = LogableImpl(logger)