package lexi.internal

import lexi.Logable
import lexi.Logger

@PublishedApi
internal class LogableImpl(
    override val logger: Logger
) : Logable