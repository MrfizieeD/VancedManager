package com.vanced.manager.ui.components.menu

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.vanced.manager.ui.components.text.ManagerText

@Composable
fun ManagerDropdownMenuItem(
    title: String,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        onClick = onClick,
    ) {
        ManagerText(
            text = title,
            textStyle = MaterialTheme.typography.body1
        )
    }
}