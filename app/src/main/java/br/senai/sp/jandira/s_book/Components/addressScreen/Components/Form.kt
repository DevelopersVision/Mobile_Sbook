package br.senai.sp.jandira.s_book.Components.addressScreen.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.Components.UniversalScreen.TextBoxScreen

@Preview(showSystemUi = true)
@Composable
fun Form(){

    var cepState by remember {
        mutableStateOf("")
    }

    var estadoState by remember {
        mutableStateOf("")
    }

    var cidadeState by remember {
        mutableStateOf("")
    }
    var logradouroState by remember {
        mutableStateOf("")
    }

    var isChecked by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextBoxScreen(
            label = "Nome",
            valor = cepState,
            aoMudar = {
                cepState = it
            }
        )
        TextBoxScreen(
            label = "Email",
            valor = estadoState,
            aoMudar = {
                estadoState = it
            }
        )
        TextBoxScreen(
            label = "cpf",
            valor = cidadeState,
            aoMudar = {
                cidadeState = it
            }
        )
        TextBoxScreen(
            label = "Data de Nascimento",
            valor = logradouroState,
            aoMudar = {
                logradouroState = it
            }
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(start = 32.dp)
        ){
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                }
            )

            Text(
                text = "Concordo com os termos & politicas",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF9F9898),
                ),
                modifier = Modifier
                    .padding(top = 7.dp)
            )
        }
    }
}


