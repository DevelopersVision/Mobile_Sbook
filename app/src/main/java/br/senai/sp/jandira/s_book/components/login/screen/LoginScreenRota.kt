package br.senai.sp.jandira.s_book.components.login.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.components.login.components.Form
import br.senai.sp.jandira.s_book.components.login.components.Header
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.components.universal.GoogleScreen
import br.senai.sp.jandira.s_book.components.universal.TextContinueScreen
import br.senai.sp.jandira.s_book.components.universal.TextNotContScreen
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.components.login.components.TextContinueScreen2
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
fun LoginScreenRota(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope?
) {
    var emailState by remember { mutableStateOf("") }
    var senhaState by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) } // Variável para controlar a visibilidade da ProgressBar
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
//            if (!isLoading) {
                Form(
                    emailState,
                    senhaState,
                    onEmailChange = { emailState = it },
                    onSenhaChange = { senhaState = it },
                    navController
                )

                DefaultButtonScreen(text = "Entrar", onClick = {
//                    isLoading = true // Mostra a ProgressBar antes de chamar a função de login
                    login(emailState, senhaState, lifecycleScope!!, context, navController) {
//                        isLoading = false
                        Toast.makeText(
                            context, it, Toast.LENGTH_LONG
                        ).show()
                    }
//                    isLoading =  false
                })

                Spacer(modifier = Modifier.height(30.dp))
                TextContinueScreen2()
                TextNotContScreen(navController)
                Spacer(modifier = Modifier.height(30.dp))
//            } else {
//                ProgressBar(isDisplayed = true)
//            }
        }
    }
}