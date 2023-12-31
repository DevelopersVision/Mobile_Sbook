package br.senai.sp.jandira.s_book.components.conversation_chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CardFotoMessageCliente(
    menssagem: String,
    hora: String,
    cor: Color,
    foto: String,
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Card(
            shape = RoundedCornerShape(
                topStart = 0.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp
            ),
            backgroundColor = cor
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .width(280.dp)
            ) {
                AsyncImage(
                    model = foto,
                    contentDescription = "",
                    modifier = Modifier.size(280.dp)
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
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