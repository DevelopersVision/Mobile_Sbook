package br.senai.sp.jandira.s_book.Components.CategoryScreen.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Preview(showSystemUi = true)
@Composable
fun Header(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Escolha suas categorias ",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
            Text(
                text = "Pular",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF808080))
        }
        Row (
            modifier = Modifier
                .width(211.dp)
                .height(30.dp)
        ) {
            Text(
                text = "Venha conhecer seu novo gênero preferido",
                fontSize = 13.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF808080)
            )
            Icon(
                painter =
                    painterResource(
                        id = R.drawable.estrelhinha
                    ),
                contentDescription = ""
            )
        }
    }
}