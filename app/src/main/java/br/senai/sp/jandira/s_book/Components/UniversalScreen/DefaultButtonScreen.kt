package br.senai.sp.jandira.s_book.Components.UniversalScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultButtonScreen(
    text: String,
    onClick: () -> Unit,

) {
    Row(
        modifier = Modifier
            .shadow(elevation = 40.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
            .width(300.dp)
            .height(48.dp)
            .background(Color(170,98,49), shape = RoundedCornerShape( size = 4.dp))
            .padding(),
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
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
    DefaultButtonScreen(
        text = "RediscoverPasswordScreen",
        onClick = {}
    )
}