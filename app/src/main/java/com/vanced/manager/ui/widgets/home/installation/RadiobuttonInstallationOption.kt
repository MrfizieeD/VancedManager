package com.vanced.manager.ui.widgets.home.installation

import androidx.annotation.StringRes
import com.vanced.manager.ui.components.preference.RadiobuttonDialogPreference
import com.vanced.manager.ui.preferences.ManagerPreference
import com.vanced.manager.ui.preferences.RadioButtonPreference

data class RadiobuttonInstallationOption(
    @StringRes val title: Int,
    val preference: ManagerPreference<String>,
    val buttons: List<RadioButtonPreference>
) : InstallationOption(
    item = {
        RadiobuttonDialogPreference(
            preferenceTitle = title,
            preference = preference,
            buttons = buttons
        )
    }
)