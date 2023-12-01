package br.senai.sp.jandira.s_book.components.advertiser.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import coil.compose.AsyncImage

@Composable
fun CardUserAdvertiser(
    nome: String,
    foto: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.clip(CircleShape).size(100.dp),
            model = foto,
            contentDescription = "image description",
            contentScale = ContentScale.Crop
        )
        Text(
            text = nome,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF)
            )
        )
    }
}