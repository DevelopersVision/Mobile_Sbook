package br.senai.sp.jandira.s_book.components.category.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R

//@Preview(showSystemUi = true)
@Composable
fun Header(
    navController: NavController
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
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
                color = Color(0xFF808080),
                modifier = Modifier
                    .clickable {
                        navController.navigate("login")
                    }
            )
        }
        Row {
            Text(
                text = "Venha conhecer seu novo gênero preferido",
                fontSize = 13.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF808080)
            )
            Image(
                painter = painterResource(id = R.drawable.estrelhinha),
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}