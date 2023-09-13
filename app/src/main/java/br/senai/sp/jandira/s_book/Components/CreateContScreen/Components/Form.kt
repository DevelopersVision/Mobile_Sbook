package br.senai.sp.jandira.s_book.Components.CreateContScreen.Components


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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.Components.UniversalScreen.DefaultButtonScreen
import br.senai.sp.jandira.s_book.Components.UniversalScreen.TextBoxScreen
import br.senai.sp.jandira.s_book.Components.UniversalScreen.TextFildPasswordScreen
import br.senai.sp.jandira.s_book.R

@Preview(showSystemUi = true)
@Composable
fun Form(){

    var emailState by remember {
        mutableStateOf("")
    }

    var nomeState by remember {
        mutableStateOf("")
    }

    var cpfState by remember {
        mutableStateOf("")
    }
    var dataNascimentoState by remember {
        mutableStateOf("")
    }

    var senhaState by remember {
        mutableStateOf("")
    }
    var redefinirsenhaState by remember {
        mutableStateOf("")
    }




    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextBoxScreen(
            label = "Nome",
            valor = nomeState,
            aoMudar = {
                nomeState = it
            }
        )
        TextBoxScreen(
            label = "Email",
            valor = emailState,
            aoMudar = {
                emailState = it
            }
        )
        TextBoxScreen(
            label = "cpf",
            valor = cpfState,
            aoMudar = {
                cpfState = it
            }
        )
        TextBoxScreen(
            label = "Data de Nascimento",
            valor = dataNascimentoState,
            aoMudar = {
                dataNascimentoState = it
            }
        )
        TextFildPasswordScreen(
            label = "Senha" ,
            valor = senhaState,
            aoMudar ={
                senhaState = it
            }
        )
        TextFildPasswordScreen(
            label = "Confirmar a senha " ,
            valor = redefinirsenhaState,
            aoMudar ={
                redefinirsenhaState = it
            }
        )
        BottonScreen(
            text = "Continar"
        ) {}
    }

}
