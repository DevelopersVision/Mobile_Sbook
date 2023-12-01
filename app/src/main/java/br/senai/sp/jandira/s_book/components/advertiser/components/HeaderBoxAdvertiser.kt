package br.senai.sp.jandira.s_book.components.advertiser.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R

@Composable
fun HeaderBoxAdvertiser(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF5E3D27),
                shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp),
            ) .padding(horizontal = 16.dp).padding(top = 24.dp, bottom = 76.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_left_gray),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Text(
                text = "Perfil",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight(600),
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(52.dp)
            )
        }
        CardUserAdvertiser(nome = "Luiz Gustavo", foto = "https://i.pinimg.com/474x/5a/21/d6/5a21d644fdd9fe17222b40db1378914b.jpg")
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            InformacaoAdvertiser(nome = "Email", valor = "luizgustavo.sp2020@gmail.com")
            InformacaoAdvertiser(nome = "Endereco", valor = "Barueri, SP")
        }
    }
}