package br.senai.sp.jandira.s_book.components.conversation_chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import coil.compose.AsyncImage

//@Preview(showSystemUi = true)
@Composable
fun Header(
    foto: String,
    nome: String,
    onclick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().background(Color.White).padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(painter = painterResource(id = R.drawable.seta_voltar),
            contentDescription = "",
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    onclick()
                })
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(56.dp),
            model = foto,
            contentDescription = ""
        )
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = nome,
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            Card(
                modifier = Modifier.size(8.dp), backgroundColor = Color.Black
            ) {}
            Card(
                modifier = Modifier.size(8.dp), backgroundColor = Color.Black
            ) {}
            Card(
                modifier = Modifier.size(8.dp), backgroundColor = Color.Black
            ) {}
        }
    }
}