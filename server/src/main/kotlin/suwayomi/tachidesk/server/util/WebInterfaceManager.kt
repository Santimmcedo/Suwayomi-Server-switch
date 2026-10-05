package suwayomi.tachidesk.server.util

import io.javalin.config.JavalinConfig
import kotlinx.coroutines.flow.MutableStateFlow
import suwayomi.tachidesk.graphql.types.*

object WebInterfaceManager {
    val isSetupComplete = MutableStateFlow(true)
    val status = MutableStateFlow(
        WebUIUpdateStatus(
            info = WebUIUpdateInfo(WebUIChannel.STABLE, "lite"),
            state = UpdateState.IDLE,
            progress = 0
        )
    )

    fun setup(config: JavalinConfig) {}
    
    fun getAboutInfo(): AboutWebUI {
        return AboutWebUI(
            channel = WebUIChannel.STABLE,
            tag = "lite",
            updateTimestamp = 0L
        )
    }
    
    suspend fun isUpdateAvailable(flavor: WebUIFlavor, raiseError: Boolean = false): Pair<String, Boolean> = Pair("lite", false)
    
    fun startDownloadInScope(flavor: WebUIFlavor, version: String) {}
    
    suspend fun getStatus(version: String, state: UpdateState): WebUIUpdateStatus = status.value
    
    suspend fun resetStatus(): WebUIUpdateStatus = status.value
}
