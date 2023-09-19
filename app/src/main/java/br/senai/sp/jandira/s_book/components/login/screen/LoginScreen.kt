package br.senai.sp.jandira.s_book.components.login.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.s_book.components.login.components.Form
import br.senai.sp.jandira.s_book.components.login.components.Header
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.components.universal.GoogleScreen
import br.senai.sp.jandira.s_book.components.universal.TextContinueScreen
import br.senai.sp.jandira.s_book.components.universal.TextNotContScreen
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.components.create_account.components.caixa
import br.senai.sp.jandira.s_book.model.CreateAccountView
import br.senai.sp.jandira.s_book.repository.LoginRepository
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope?
) {

    var emailState by remember {
        mutableStateOf("")
    }
    var senhaState by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Header()
            Form(emailState, senhaState, onEmailChange = { emailState = it },
                onSenhaChange = { senhaState = it }, navController)

            DefaultButtonScreen(text = "Entrar", onClick = {
                login(emailState, senhaState, lifecycleScope!!)
            })

            TextContinueScreen()
            GoogleScreen()
            TextNotContScreen(navController)
        }
    }
}

fun login(email: String, senha: String, lifecycleScope: LifecycleCoroutineScope) {

    val loginRepository = LoginRepository()
    lifecycleScope.launch {
        val response = loginRepository.loginUsuario(email, senha)

        if (response.isSuccessful) {
            Log.e("login", "login: ${response.body()}")
        } else {
            val erroBody = response.errorBody()?.string()

            Log.e("login", "login: $erroBody")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()

    LoginScreen(
        navController = navController,
        lifecycleScope = null
    )
}