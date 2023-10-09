package br.senai.sp.jandira.s_book.components.cep.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

@Preview(showSystemUi = true)
@Composable
fun Header(){

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(245.dp)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(27.dp, Alignment.Top),
    ) {
        Column(){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.height(13.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(1.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(start = 32.dp),) {
                Text(
                    text = "Criar Conta",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(800),
                        color = Color.Black,
                    )
                )
            }

        }

        Column (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .padding(start = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        ) {
            Column(
                modifier = Modifier
                    .width(280.dp)
                    .height(70.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.Top),
            ){
                Text(
                    text = "Adicionar Endereço no Cadastro",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
                Text(
                    text = "Agora, Insira o CEP para cadastrar seu\nendereço",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            }
        }

    }

}