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
import br.senai.sp.jandira.s_book.components.universal.TextBoxScreen
import br.senai.sp.jandira.s_book.components.universal.TextFieldPasswordScreen

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
        TextFieldPasswordScreen(
            label = "Senha" ,
            valor = senhaState,
            aoMudar ={
                senhaState = it
            }
        )
        TextFieldPasswordScreen(
            label = "Confirmar a senha " ,
            valor = redefinirsenhaState,
            aoMudar ={
                redefinirsenhaState = it
            }
        )
        Button(
            text = "Continar"
        ) {}
    }

}
