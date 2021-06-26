package com.vanced.manager.ui.components.color

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.orchestra.colorpicker.ColorPicker

@Composable
fun ManagerColorPicker(
    onColorSelected: (color: Long) -> Unit
) {
    val (selectedColor, setColor) = remember { mutableStateOf(ColorEnvelope(0)) }
    ColorPicker(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        onColorListener = { envelope, _ -> setColor(envelope) },
        children = {}
    )
    onColorSelected(selectedColor.color.toLong())
}