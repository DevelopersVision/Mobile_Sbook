package br.senai.sp.jandira.s_book.components.profile.components

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

@Preview
@Composable
fun ButtonProfile() {

    TextButton(
        onClick = {},
        modifier = Modifier
            .shadow(
                elevation = 40.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .width(300.dp)
            .height(48.dp)
            .background(Color(0xFFDA6C27), shape = RoundedCornerShape(size = 4.dp))
            .padding()
    ) {
        Text(
            text = "",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.intermedium)),
            fontWeight = FontWeight(600),
            color = Color(0xFFFFFFFF),
        )
    }

}