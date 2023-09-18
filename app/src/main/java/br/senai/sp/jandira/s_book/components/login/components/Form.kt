package br.senai.sp.jandira.s_book.components.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.components.universal.TextBoxScreen
import br.senai.sp.jandira.s_book.components.universal.TextFieldPasswordScreen

@Composable
fun Form(email: String, senha: String, onEmailChange: (String) -> Unit, onSenhaChange: (String) -> Unit, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextBoxScreen(
            label = "Email",
            valor = email,
            aoMudar = {
                onEmailChange(it) // Chame a função de callback para atualizar o email
            }
        )
        TextFieldPasswordScreen(
            label = "Senha",
            valor = senha,
            aoMudar = {
                onSenhaChange(it) // Chame a função de callback para atualizar a senha
            }
        )

        Text(
            text = "Esqueci a senha",
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 45.dp)
                .clickable {
                           navController.navigate("forgot_password")
                },
            color = Color(159, 152, 152),
            textAlign = TextAlign.End
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun FormPreview() {
    var navController = rememberNavController()
    var emailState by remember {
        mutableStateOf("")
    }
    var senhaState by remember {
        mutableStateOf("")
    }

    // Chame o Form com as funções de callback para atualizar o email e a senha
    Form(
        email = emailState,
        senha = senhaState,
        onEmailChange = { emailState = it },
        onSenhaChange = { senhaState = it },
        navController = navController
    )
}
