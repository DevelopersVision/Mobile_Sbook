package br.senai.sp.jandira.s_book.components.login.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import br.senai.sp.jandira.s_book.model.UsuarioJSon
import br.senai.sp.jandira.s_book.repository.LoginRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject

@Composable
fun LoginScreen(
    navController: NavController, lifecycleScope: LifecycleCoroutineScope?
) {

    var emailState by remember {
        mutableStateOf("")
    }
    var senhaState by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Header()
            Form(
                emailState,
                senhaState,
                onEmailChange = { emailState = it },
                onSenhaChange = { senhaState = it },
                navController
            )

            DefaultButtonScreen(text = "Entrar", onClick = {
                Log.e("Teste", "LoginScreen: Click" )
                login(emailState, senhaState, lifecycleScope!!, context, navController)
            })

            TextContinueScreen()
            GoogleScreen()
            TextNotContScreen(navController)
        }
    }
}

fun login(email: String, senha: String, lifecycleScope: LifecycleCoroutineScope, context: Context, navController: NavController) {

    val validacaoDados = dataValidation(email, senha)

    if (validacaoDados) {
        val loginRepository = LoginRepository()

        lifecycleScope.launch {
            val response = loginRepository.loginUsuario(email, senha)
            val code = response.code()

            if (response.isSuccessful) {

                val jsonString = response.body().toString() // Converta a resposta em uma string JSON
                val jsonObject = JSONObject(jsonString) // Converta a string JSON em um objeto JSONObject
                val usuarioObject = jsonObject.getJSONObject("usuario")

                val userObject = usuarioObject.getJSONObject("usuario")
                val nome = userObject.getString("nome")

                Log.e("LOGIN - SUCESS - 201", "login: ${response.body()}")
                Toast.makeText(context, "Bem Vindo $nome", Toast.LENGTH_SHORT).show()

                navController.navigate("navigation_home_bar")
            } else {

                when (code) {
                    404 -> {
                        Log.e("LOGIN - ERROR - 404", "login: ${response.errorBody()?.string()}")
                        Toast.makeText(
                            context, "O EMAIL OU SENHA INFORMADO NÃO É VALIDADO", Toast.LENGTH_LONG
                        ).show()
                    }
                    500 -> {
                        Log.e("LOGIN - ERROR - 500", "login: ${response.errorBody()?.string()}")
                        Toast.makeText(context, "SERVIDOR INDISPONIVEL NO MOMENTO", Toast.LENGTH_LONG)
                            .show()
                    }
                    400 -> {
                        Log.e("LOGIN - ERROR - 400", "login: ${response.errorBody()?.string()}")
                        Toast.makeText(
                            context,
                            "NÃO FORAM PREENCHIDO TODOS OS CAMPOS OBRIGATÓRIOS",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    403 -> {
                        Log.e("LOGIN - ERROR - 403", "login: ${response.errorBody()?.string()}")
                        Toast.makeText(context, "A CONTA ESTÁ DESATIVADA", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    } else {
        Log.e("LOGIN - ERROR", "login")
        Toast.makeText(context, "EMAIL OU SENHA NÃO INSERIDO CORRETAMENTE", Toast.LENGTH_LONG).show()
    }
}

fun dataValidation(email: String, senha: String): Boolean {
    return !(email == "" || email.length > 255 || senha == "")
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()

    LoginScreen(
        navController = navController, lifecycleScope = null
    )
}