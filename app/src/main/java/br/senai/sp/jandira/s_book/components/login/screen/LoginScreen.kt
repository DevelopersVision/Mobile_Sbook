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
import br.senai.sp.jandira.s_book.components.universal.ProgressBar
import br.senai.sp.jandira.s_book.functions.dataValidation
import br.senai.sp.jandira.s_book.functions.deleteUserSQLite
import br.senai.sp.jandira.s_book.functions.saveLogin
import br.senai.sp.jandira.s_book.repository.LoginRepository
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.json.JSONObject

@Composable
fun LoginScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope?
) {
    var emailState by remember { mutableStateOf("") }
    var senhaState by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) } // Variável para controlar a visibilidade da ProgressBar
    var pesquisaState by remember { mutableStateOf("") }
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
            if (isLoading == false) {
                Form(
                    emailState,
                    senhaState,
                    onEmailChange = { emailState = it },
                    onSenhaChange = { senhaState = it },
                    navController
                )

                DefaultButtonScreen(text = "Entrar", onClick = {
                    isLoading = true // Mostra a ProgressBar antes de chamar a função de login
                    login(emailState, senhaState, lifecycleScope!!, context, navController)
                    isLoading =  true
                })

                TextContinueScreen()
                GoogleScreen()
                TextNotContScreen(navController)
            } else {
                ProgressBar(isDisplayed = isLoading)
            }
        }
    }
}

fun login(
    email: String,
    senha: String,
    lifecycleScope: LifecycleCoroutineScope,
    context: Context,
    navController: NavController
) {

    val validacaoDados = dataValidation(email, senha)

    if (validacaoDados) {
        val loginRepository = LoginRepository()

        lifecycleScope.launch {
            val response = loginRepository.loginUsuario(email, senha)
            val code = response.code()

            if (response.isSuccessful) {

                val jsonString =
                    response.body().toString() // Converta a resposta em uma string JSON
                val jsonObject =
                    JSONObject(jsonString) // Converta a string JSON em um objeto JSONObject
                val usuarioObject = jsonObject.getJSONObject("usuario")

                val userObject = usuarioObject.getJSONObject("usuario")
                val enderecoObject = usuarioObject.getJSONObject("endereco")

                val id = userObject.getLong("id")
                val nome = userObject.getString("nome")
                val token = jsonObject.getString("token")
                val email = userObject.getString("email")
                val foto = userObject.getString("foto")
                val cpf = userObject.getString("cpf")
                val dataNascimento = userObject.getString("data_nascimento")
                val cep = enderecoObject.getString("cep")
                val logradouro = enderecoObject.getString("logradouro")
                val bairro = enderecoObject.getString("bairro")
                val cidade = enderecoObject.getString("cidade")
                val ufEstado = enderecoObject.getString("estado")
                val idEndereco = enderecoObject.getInt("id")

                Log.e("LOGIN - SUCESS - 201", "login: ${response.body()}")
                Toast.makeText(context, "Bem Vindo $nome", Toast.LENGTH_SHORT).show()


                if (UserRepository(context).findUsers().isEmpty()){
                    saveLogin(
                        context = context,
                        id = id,
                        nome = nome,
                        token = token,
                        email = email,
                        cep = cep,
                        idEndereco = idEndereco,
                        foto = foto,
                        dataNascimento = dataNascimento,
                        logradouro = logradouro,
                        bairro = bairro,
                        cidade = cidade,
                        ufEstado = ufEstado,
                        senha = senha,
                        cpf = cpf
                    )

                    navController.navigate("navigation_home_bar")
                }else{
                    deleteUserSQLite(context = context, id.toInt())

                    saveLogin(
                        context = context,
                        id = id,
                        nome = nome,
                        token = token,
                        email = email,
                        cep = cep,
                        idEndereco = idEndereco,
                        foto = foto,
                        dataNascimento = dataNascimento,
                        logradouro = logradouro,
                        bairro = bairro,
                        cidade = cidade,
                        ufEstado = ufEstado,
                        senha = senha,
                        cpf = cpf
                    )

                    navController.navigate("navigation_home_bar")
                }

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
                        Toast.makeText(
                            context,
                            "SERVIDOR INDISPONIVEL NO MOMENTO",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                    400 -> {
                        Log.e("LOGIN - ERROR - 400", "login: ${response.errorBody()?.string()}")

                        Toast.makeText(
                            context,
                            "A SENHA INFORMADA NÃO É VALIDADA",
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
        Toast.makeText(context, "EMAIL OU SENHA NÃO INSERIDO CORRETAMENTE", Toast.LENGTH_LONG)
            .show()
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()

    LoginScreen(
        navController = navController, lifecycleScope = null
    )
}