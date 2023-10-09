package br.senai.sp.jandira.s_book.components.perfil.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository

@Composable
fun Dados(
    navController: NavController
) {

    val context = LocalContext.current

    val dadaUser = UserRepository(context).findUsers()

    var array = User()

    var data = ""

    if(dadaUser.isNotEmpty()){
        array = dadaUser[0]

        data = converterData(array.dataNascimento)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(440.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .width(500.dp)
                .height(90.dp)
                .padding(start = 45.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Email",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(170, 98, 49),

                )
            Text(
                text = array.email,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .width(300.dp)
                    .height(2.dp)
                    .background(color = Color(0xFFCECECE))
            ) {}
        }

        Column(
            modifier = Modifier
                .width(500.dp)
                .height(90.dp)
                .padding(start = 45.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Cpf",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(170, 98, 49),

                )
            Text(
                text = array.cpf,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )

            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .width(300.dp)
                    .height(2.dp)
                    .background(color = Color(0xFFCECECE))
            ) {}
        }
        Column(
            modifier = Modifier
                .width(500.dp)
                .height(90.dp)
                .padding(start = 45.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Data de nascimento",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(170, 98, 49),

                )
            Text(
                text = data,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )

            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .width(300.dp)
                    .height(2.dp)
                    .background(color = Color(0xFFCECECE))
            ) {}
        }
        Column(
            modifier = Modifier
                .width(500.dp)
                .height(90.dp)
                .padding(start = 45.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Cep",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(170, 98, 49),

                )
            Text(
                text = array.cep,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )

            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .width(300.dp)
                    .height(2.dp)
                    .background(color = Color(0xFFCECECE))
            ) {}
        }
        Spacer(modifier = Modifier.height(12.dp))
        ButtonExit(
            text = "Sair"
        ) {
            UserRepository(context).deleteUser(array.id.toInt())

            navController.navigate("navigation_home_bar")
        }
    }
}

fun converterData(data: String): String {
    // Substituir o caractere de hífen pelo caractere de barra
    return data.replace("-", "/")
}