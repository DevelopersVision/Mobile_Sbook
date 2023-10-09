package br.senai.sp.jandira.s_book.components.universal


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TextNotContScreen(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),

        ) {
        Text(
            text = "Não tem conta?",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
        )

        Text(
            text = "Cadastre-se aqui.",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                color = Color(170, 98, 49)
            ),
            modifier = Modifier
                .clickable {
                    navController.navigate("create_account")
                }
        )

    }
}


//Cadastre-se aqui.