package br.senai.sp.jandira.s_book.components.create_account_endereco.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.components.universal.TextBoxScreen
import br.senai.sp.jandira.s_book.components.universal.TextFieldPasswordScreen

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
    var bairroState by remember {
        mutableStateOf("")
    }


    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextBoxScreen(
            label = "CEP",
            valor = cepState,
            aoMudar = {
                cepState = it
            }
        )
        TextBoxScreen(
            label = "Estado",
            valor = estadoState,
            aoMudar = {
                estadoState = it
            }
        )
        TextBoxScreen(
            label = "Cidade",
            valor = cidadeState,
            aoMudar = {
                cidadeState = it
            }
        )
        TextBoxScreen(
            label = "bairro",
            valor = bairroState,
            aoMudar = {
                bairroState = it
            }
        )
        TextBoxScreen(
            label = "Logradouro",
            valor = logradouroState,
            aoMudar = {
                logradouroState = it
            }
        )
        DefaultButtonScreen(text = "Entrar") {

        }
    }

}
