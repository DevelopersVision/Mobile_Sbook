package br.senai.sp.jandira.s_book.components.universal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun  HeaderCreateAnnounce(
//    onclick : () -> Unit
){
    Column() {

        val flecha = Icons.Default.ArrowBackIos

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(123.dp)
        ) {
            IconButton(
                onClick = {
//                    onclick()
                }
            ) {
                Icon(
                    imageVector = flecha,
                    contentDescription = "",
                    modifier = Modifier
                        .size(28.dp),
                    tint = Color(0xFFAA6231)
                )
            }
            Text(
                text = "Anunciar",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.8.dp),
            color = Color(0xFFE0E0E0)
        )
    }
}