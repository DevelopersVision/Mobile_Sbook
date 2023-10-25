package br.senai.sp.jandira.s_book.components.rediscover_password.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.rediscover_password.components.Form
import br.senai.sp.jandira.s_book.components.rediscover_password.components.Header
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.view_model.ResetPasswordView
import br.senai.sp.jandira.s_book.repository.ChangePasswordRepository
import kotlinx.coroutines.launch

@Composable
fun RediscoverPasswordScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ResetPasswordView
){
    val contexto = LocalContext.current

    var passwordState by remember {
        mutableStateOf("")
    }

    var confirmPasswordState by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header(text = "Recuperação de senha")
            Form(
                passwordState = passwordState,
                confirmPasswordState = confirmPasswordState,
                onPasswordChange = {passwordState = it},
                onConfirmPasswordChange = {confirmPasswordState = it}
            )
            Spacer(modifier = Modifier.height(30.dp))
            DefaultButtonScreen(text = "Redefinir Senha") {
                changePassword(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                    viewModel = viewModel,
                    senha = passwordState,
                    confirmarSenha = confirmPasswordState,
                    contexto
                )
            }
        }

    }
}

fun changePassword (
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ResetPasswordView,
    senha: String,
    confirmarSenha: String,
    contexto: Context
) {
    if(senha == confirmarSenha){
        val id = viewModel.id
        val changePasswordRepository = ChangePasswordRepository()

        lifecycleScope.launch {
            val response = changePasswordRepository.mudarSenha(id, senha)

            Log.e("Tag", "changePassword: $response", )

            if(response.isSuccessful){
                navController.navigate("login")
            }else{
                val erroBody = response.errorBody()?.string()

                Log.e("reset de senha", "reset de senha: $erroBody")
            }
        }
    }else{
        Toast.makeText(contexto, "As senha estão diferentes", Toast.LENGTH_SHORT).show()
    }
}