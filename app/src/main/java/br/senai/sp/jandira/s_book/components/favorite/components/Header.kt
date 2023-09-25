package br.senai.sp.jandira.s_book.components.favorite.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.navigation_home_bar.BottomBarScreen

@Preview(showSystemUi = true)
@Composable
fun Header(){

    var sair = Icons.Default.ArrowBackIos

    Column (
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(color = Color.White)
        ,
        ) {
        Row (
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            IconButton(
                modifier = Modifier
                    .width(30.dp)
                    .height(42.dp),
                onClick = { /*TODO*/ }
            ) {
               Icon(
                   imageVector = sair,
                   contentDescription = "",
                   modifier = Modifier
                       .width(30.dp)
                       .height(32.dp),
                   tint = Color(0xFF784F34)
               )
            }

            Text(
                text = "Meus favoritos",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF808080),
            )

            Image(
                painter =
                painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .width(52.dp)
                    .height(51.48207.dp),
            )
        }
    }

}