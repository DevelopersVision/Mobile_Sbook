package br.senai.sp.jandira.s_book.components.create_account.components


import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.create_account_endereco.components.Button
import br.senai.sp.jandira.s_book.components.universal.TextBoxScreen
import br.senai.sp.jandira.s_book.components.universal.TextFieldPasswordScreen
import br.senai.sp.jandira.s_book.view_model.CreateAccountView

@Composable
fun Form(
    navController: NavController,
    viewModel: CreateAccountView,
    context: Context
) {

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

    var data by remember {
        mutableStateOf("")
    }




    Column(
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
            label = "Senha",
            valor = senhaState,
            aoMudar = {
                senhaState = it
            }
        )
        TextFieldPasswordScreen(
            label = "Confirmar a senha ",
            valor = redefinirsenhaState,
            aoMudar = {
                redefinirsenhaState = it
            }
        )
        Button(
            text = "Continar",
            onClick = {
                cadastro(
                    nomeState,
                    emailState,
                    cpfState,
                    dataNascimentoState,
                    senhaState,
                    redefinirsenhaState,
                    viewModel,
                    context,
                    navController
                )
            }
        )
    }
}


fun cadastro(
    nome: String?,
    email: String?,
    cpf: String?,
    dataNascimento: String?,
    senha: String?,
    confirmarSenha: String?,
    viewModel: CreateAccountView,
    context: Context,
    navController: NavController
) {
    if (
        nome == null || nome == "" ||
        email == null || email == "" ||
        cpf == null || cpf == "" ||
        dataNascimento == null || dataNascimento == "" ||
        senha == null || senha == ""
    ) {
        Log.e("CADASTRO - ERROR", "CADASTRO_V1: REQUIRE FIELDS")
        Toast.makeText(context, "NÃO FOI PREENCHIDO TODOS OS CAMPOS", Toast.LENGTH_SHORT).show()
    } else if (senha == confirmarSenha) {
        viewModel.nome = nome
        viewModel.email = email
        viewModel.cpf = cpf
        viewModel.dataNascimento = dataNascimento
        viewModel.senha = senha

        navController.navigate("create_account_endereco")
    } else {
        Log.e("CADASTRO - ERROR", "CADASTRO_V1: SENHA DIFERENTES")
        Toast.makeText(context, "AS SENHA ESTÃO DIFERENTES", Toast.LENGTH_SHORT).show()
    }
}
