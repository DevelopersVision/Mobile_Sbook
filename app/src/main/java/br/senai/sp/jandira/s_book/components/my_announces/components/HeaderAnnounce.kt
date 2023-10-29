package br.senai.sp.jandira.s_book.components.my_announces.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R


@Composable
fun HeaderAnnounce(
    onclick: ()-> Unit,
) {



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
        backgroundColor = Color(0xFF784F34),
        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
    ){
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_left_white),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onclick()
                        }
                )
                Text(
                    text = "Meus anúncios",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight(600)
                )
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier.size(52.dp)
                )
            }
            Column {
                Text(
                    text = "Olá, Maria",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF)
                )
                Text(
                    text = "Você tem 1 anúncio",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                androidx.compose.material3.Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(221, 163, 93, 255)),
                    modifier = Modifier.size(64.dp),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }

}