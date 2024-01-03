@file:JsExport

package lexi.builder

import kotlinx.JsExport
import kotlin.jvm.JvmOverloads

class ConsoleAppenderConfigurationBuilder @JvmOverloads constructor(
    enabled: Boolean = true
) : AppenderConfigurationBuilder(null, enabled)