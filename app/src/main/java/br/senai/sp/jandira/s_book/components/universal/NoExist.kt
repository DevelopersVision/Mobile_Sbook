package br.senai.sp.jandira.s_book.components.universal


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

@Composable
fun NoExist(
    onclick: ()-> Unit,
    textTitulo: String,
    textSubTitulo: String,
    textDecisão:String
) {

    Column(
        modifier = Modifier
            .width(360.dp)
            .height(300.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .width(120.dp)
                .height(102.dp),
            painter = painterResource(id = R.drawable.carinho),
            contentDescription = "image description",
            contentScale = ContentScale.Crop
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = textTitulo,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(500),
                color = Color(0xFFAA6231),
                textAlign = TextAlign.Center
            )
            Text(
                text = textSubTitulo,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center
            )
        }
        TextButton(
            onClick = {
                onclick()
            }
        ) {
            Text(
                text = textDecisão,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(400),
                color = Color(0xFFAA6231),
                textAlign = TextAlign.Center,
            )
        }
    }
}

//Nenhum favorito ainda :(
//Escolha o que você mais gostou