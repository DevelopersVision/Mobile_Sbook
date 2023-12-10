package br.senai.sp.jandira.s_book.components.conversation_chat.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R


@Composable
fun CardMensagemUser(
    menssagem: String,
    hora: String,
    cor: Color,
    onDelete: () -> Unit,
    maxBubbleWidth: Dp = 200.dp,
) {
    var isLongPressActive by remember { mutableStateOf(false) }
    var isLongPressStarted by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            modifier = Modifier
                .pointerInput(Unit) {

                    detectTransformGestures { _, pan, _, _ ->
                        if (pan != Offset(0f, 0f)) {
                            isLongPressActive = true
                        }
                    }
                }
                .onFocusChanged {
                    isLongPressActive = false
                },
            backgroundColor = if (isLongPressActive) {
                Color.Gray
            } else {
                cor
            },
            shape = RoundedCornerShape(8.dp)
        ) {
            if (!isLongPressActive) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(12.dp)
                ) {

                    if (menssagem.length > 25) {
                        Text(
                            text = menssagem,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .width(maxBubbleWidth)
                                .padding(end = 10.dp),
                            color = Color.Black
                        )
                    } else {
                        Text(
                            text = menssagem,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .width(IntrinsicSize.Max)
                                .padding(end = 10.dp),
                            color = Color.Black
                        )
                    }

                    Text(
                        text = hora,
                        fontSize = 8.sp,
                        color = Color.Black
                    )


                }
            } else {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(12.dp)
                    ) {

                        if (menssagem.length > 25) {
                            Text(
                                text = menssagem,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .width(maxBubbleWidth)
                                    .padding(end = 10.dp),
                                color = Color.Black
                            )
                        } else {
                            Text(
                                text = menssagem,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .padding(end = 10.dp),
                                color = Color.Black
                            )
                        }

                        Text(
                            text = hora,
                            fontSize = 8.sp,
                            color = Color.Black
                        )

                    }

                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(8.dp)
                            .clickable {
                                onDelete()
                                isLongPressActive = false
                            }
                    )
                }
            }

        }

    }
}

