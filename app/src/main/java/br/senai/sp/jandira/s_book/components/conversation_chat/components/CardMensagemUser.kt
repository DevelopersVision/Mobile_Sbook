package br.senai.sp.jandira.s_book.components.conversation_chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    envio : Int
) {

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Card(
            shape = RoundedCornerShape(
                topStart = 16.dp, topEnd = 0.dp, bottomStart = 16.dp, bottomEnd = 16.dp
            ),
            modifier = Modifier.width(280.dp),
            backgroundColor = cor
        ) {
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
                Text(
                    text = "$envio"
                )
            }
        }
    }

}