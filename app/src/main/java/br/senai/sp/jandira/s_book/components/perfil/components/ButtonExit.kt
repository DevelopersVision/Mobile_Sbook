package br.senai.sp.jandira.s_book.components.perfil.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonExit(
    text: String,
    onClick: () -> Unit,

    ) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .shadow(
                elevation = 40.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .width(300.dp)
            .height(48.dp)
            .background(
                Color(221, 163, 93, ),
                    shape = RoundedCornerShape(size = 4.dp))
            .padding()
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultButtonScreenPreview() {
    ButtonExit(
        text = "Sair",
        onClick = {}
    )
}