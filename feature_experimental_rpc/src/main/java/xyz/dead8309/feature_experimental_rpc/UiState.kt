/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * UiState.kt is part of Kizzy
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package xyz.dead8309.feature_experimental_rpc

import androidx.compose.runtime.Immutable
import com.my.kizzy.data.utils.AppsInfo

@Immutable
data class UiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAppsRpcPartEnabled: Boolean = true,
    val isMediaRpcPartEnabled: Boolean = true,
    val templateName: String = "",
    val templateDetails: String = "",
    val templateState: String = "",
    val installedApps: List<AppsInfo> = emptyList(),
    val enabledApps: Map<String, Boolean> = emptyMap(), // pkg -> enabled
    val appActivityTypes: Map<String, Int> = emptyMap(), // pkg -> activity type
    val isAppsLoading: Boolean = false,
    val showCoverArt: Boolean = true,
    val showAppIcon: Boolean = false,
    val showPlaybackState: Boolean = true,
    val enableTimestamps: Boolean = true,
    val hideOnPause: Boolean = false,
    val platform: String = "",
    val platformIsExpanded: Boolean = false,
    val buttonsEnabled: Boolean = false,
    val button1Text: String = "",
    val button1Url: String = "",
    val button2Text: String = "",
    val button2Url: String = "",
    val useCustomAppId: Boolean = false,
    val applicationId: String = "",
    val timestampMode: String = "default",
    val timestampModeExpanded: Boolean = false,
    val customTimestampStart: String = "",
    val customTimestampEnd: String = "",
    val showSongAsTitle: Boolean = false,
    val titleSource: String = "title", // "title" = song name, "artist" = artist name
    val titleSourceExpanded: Boolean = false
)
