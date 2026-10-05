package suwayomi.tachidesk.server.util

import io.javalin.config.JavalinConfig
import kotlinx.coroutines.flow.MutableStateFlow
import suwayomi.tachidesk.graphql.types.WebUIFlavor
import suwayomi.tachidesk.graphql.types.WebUIUpdateStatus

object WebInterfaceManager {
    val isSetupComplete = MutableStateFlow(true)
    val status = MutableStateFlow(WebUIUpdateStatus())

    fun setup(config: JavalinConfig) {}
    suspend fun getAboutInfo(): Any? = null
    suspend fun isUpdateAvailable(flavor: WebUIFlavor, raiseError: Boolean = false): Pair<String, Boolean> = Pair("", false)
    fun startDownloadInScope(flavor: WebUIFlavor, version: String) {}
    suspend fun getStatus(version: String, state: WebUIUpdateStatus.WebUIUpdateState): WebUIUpdateStatus = status.value
    suspend fun resetStatus(): WebUIUpdateStatus = status.value
}
