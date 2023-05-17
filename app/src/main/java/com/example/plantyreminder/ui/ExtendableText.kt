package com.example.plantyreminder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.plantyreminder.R

@Composable
fun ExtendableText(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle()
) {
    val popupControl = remember { mutableStateOf(false) }
    val hasOverflow = remember { mutableStateOf(false) }

    Box(modifier = Modifier.then(modifier)){
        ClickableText(
            text = AnnotatedString(text),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            onClick = {
                popupControl.value = true
            },
            onTextLayout = {
                if (it.hasVisualOverflow) hasOverflow.value = true
            },
            style = textStyle,
            modifier = Modifier.align(Alignment.Center)
        )
        if (hasOverflow.value) OverflowPopup(popupControl = popupControl, text = text)
    }
}

@Composable
private fun OverflowPopup(popupControl: MutableState<Boolean>, text: String) {
    if (!popupControl.value) return

    Popup(
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            clippingEnabled = true,
        ),
        onDismissRequest = { popupControl.value = false },
//        offset = IntOffset(0, 120)
    ) {
        Box(
            modifier = Modifier
                .background(
                    colorResource(id = R.color.gray_200),
                    RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(8.dp),
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center
            )
        }
    }
}
