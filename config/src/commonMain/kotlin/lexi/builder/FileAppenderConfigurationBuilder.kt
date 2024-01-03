@file:JsExport

package lexi.builder

import kotlinx.JsExport
import kotlin.jvm.JvmOverloads

class FileAppenderConfigurationBuilder @JvmOverloads constructor(
    internal var directory: String? = null,
    enabled: Boolean = false,
) : AppenderConfigurationBuilder(null, enabled) {
    fun directory(path: String) {
        directory = path
    }
}