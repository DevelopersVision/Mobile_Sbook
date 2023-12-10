package br.senai.sp.jandira.s_book.components.conversation_chat.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import br.senai.sp.jandira.s_book.R
import coil.compose.AsyncImage

@Composable
fun CardMensagemUserFoto(
    menssagem: String,
    hora: String,
    cor: Color,
    foto: String,
    onDelete: () -> Unit
) {

    var isLongPressActive by remember { mutableStateOf(false) }
    var isLongPressStarted by remember { mutableStateOf(false) }


    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        androidx.compose.material.Card(
            shape = RoundedCornerShape(
                topStart = 16.dp, topEnd = 0.dp, bottomStart = 16.dp, bottomEnd = 16.dp
            ),
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, _, _ ->
                        if (pan != Offset(0f, 0f)) {
                            isLongPressActive = true
                        }
                    }
                },
            backgroundColor = cor
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                AsyncImage(
                    model = foto,
                    contentDescription = "",
                    modifier = Modifier.size(280.dp)
                )
                Text(
                    text = hora,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(end = 3.dp)
                        .width(280.dp),
                    fontSize = 8.sp,
                    color = Color.Black
                )

            }

            if (isLongPressActive) {
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