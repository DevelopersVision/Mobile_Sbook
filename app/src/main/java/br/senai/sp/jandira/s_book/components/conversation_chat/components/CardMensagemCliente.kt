package br.senai.sp.jandira.s_book.components.conversation_chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardMensagemCliente(
    menssagem : String,
    hora: String,
    cor: Color,
    maxBubbleWidth: Dp = 200.dp
){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Card(
            modifier = Modifier,
            backgroundColor = cor,
            shape = RoundedCornerShape(8.dp)
        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(12.dp)
            ){
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
        }
    }
}