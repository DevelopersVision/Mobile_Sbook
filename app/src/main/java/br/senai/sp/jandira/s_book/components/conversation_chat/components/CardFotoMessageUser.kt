package br.senai.sp.jandira.s_book.components.conversation_chat.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CardFotoMessageUser(
    menssagem: String,
    hora: String,
    cor: Color,
    foto: String,
    onDelete: () -> Unit
) {
    var isLongPressActive by remember { mutableStateOf(false) }
    var isLongPressStarted by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = 16.dp, topEnd = 0.dp, bottomStart = 16.dp, bottomEnd = 16.dp
            ),
            modifier = Modifier
                .width(280.dp)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, _, _ ->
                        if (pan != Offset(0f, 0f)) {
                            isLongPressStarted = true
                            isLongPressActive = true
                        }
                    }
                },
            backgroundColor = if (isLongPressActive) Color.Black.copy(alpha = 0.2f) else cor
        ) {

            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .width(280.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(12.dp)
                            .fillMaxWidth(),
                        onDraw = {
                            drawCircle(
                                color = if (isLongPressActive) Color.Transparent else Color.Transparent
                            )
                        }
                    )
                    // Adicionando o ícone de lixeira
                    if (isLongPressActive) {
                        Column(
                            modifier = Modifier
//                                .zIndex(3f)
                                .clickable {
                                    onDelete()
                                }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }

                AsyncImage(
                    model = foto,
                    contentDescription = "",
                    modifier = Modifier.size(280.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = menssagem,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF3B4A54)
                    )
                    Text(
                        text = hora,
                        fontSize = 10.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF3B4A54),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}