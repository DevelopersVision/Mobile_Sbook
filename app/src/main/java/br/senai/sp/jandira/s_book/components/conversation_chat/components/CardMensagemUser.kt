package br.senai.sp.jandira.s_book.components.conversation_chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CardMensagemUser(
    menssagem: String,
    hora: String,
    cor: Color,
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
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                androidx.compose.foundation.Canvas(
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
                    Column (
                        modifier = Modifier
                            .clickable {
                                onDelete()
                            }
                    ){
                        // Substitua o ícone padrão pelo ícone de lixeira real que você deseja usar
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = menssagem,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000)
                )
                Text(
                    text = hora,
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF3B4A54),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}