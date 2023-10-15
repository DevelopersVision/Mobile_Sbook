package br.senai.sp.jandira.s_book.components.filters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

//@Preview(showSystemUi = true)
@Composable
fun ComponentsFiltro(
    text : String,
    icon : Painter,
    onclick : () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(start = 12.dp)
            .clickable {
                onclick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier
                .width(24.dp)
                .height(19.dp),
            tint = Color(0xFF000000)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppinsmedium)),
            fontWeight = FontWeight(500),
            color = Color(0xFF000000),
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(0.9.dp)
            .background(color = Color(0xFFE0E0E0))
    ) {}

}