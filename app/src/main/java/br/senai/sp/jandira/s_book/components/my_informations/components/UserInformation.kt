package br.senai.sp.jandira.s_book.components.my_informations.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun UserInformations() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Informações do Usuário",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFFAA6231)
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            BoxMyInformations(
                label = "Nome",
                valor = "Luiz Gustavo"
            )
            BoxMyInformations(
                label = "Email",
                valor = "luizgustavo.sp2020@gmail.com"
            )
            BoxCEP(
                label = "CEP",
                value = "06420-230",
                onValueChange = {}
            )
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//
//            }
        }
    }
}