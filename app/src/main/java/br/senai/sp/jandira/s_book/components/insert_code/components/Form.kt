package br.senai.sp.jandira.s_book.components.insert_code.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import br.senai.sp.jandira.s_book.components.forgot_password.components.resetPassword
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.components.universal.ProgressBar
import br.senai.sp.jandira.s_book.view_model.ResetPasswordView
import br.senai.sp.jandira.s_book.repository.ResetPasswordRepository
import kotlinx.coroutines.launch


@Composable
fun Form(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ResetPasswordView,
    onLoadingStateChanged: (Boolean) -> Unit // Lambda para informar o estado de carregamento
) {
    var codigoState by remember {
        mutableStateOf("")
    }

    var corState by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
        ) {
            TextFieldCode(
                "Codigo",
                codigoState
            ) {
                corState = it.isNotEmpty()
                if(it.length < 5){
                    codigoState = it
                }
            }
        }
        Spacer(modifier = Modifier.height(64.dp))

        // Mostra a ProgressBar antes de chamar a função de login
        ButtonCode(
            text = "Continuar",
            onClick = {
                onLoadingStateChanged(true) // Informa que o carregamento está ocorrendo
                insertCode(navController, lifecycleScope, viewModel, context, codigoState.toInt()){
                    onLoadingStateChanged(it) // Informa que o carregamento foi concluído
                }
            },
            corState = corState
        )
        Spacer(modifier = Modifier.height(24.dp))
        DefaultButtonScreen(
            text = "Reenviar código"
        ) {
            resetPassword(email = viewModel.email!!, lifecycleScope, viewModel, navController, context)
        }
    }
}



fun insertCode(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ResetPasswordView,
    context: Context,
    codigoState: Int,
    onLoading: (Boolean) -> Unit
) {
    val resetPasswordRepository = ResetPasswordRepository()
    lifecycleScope.launch {
        val email = viewModel.email

        if (insertCodeValidation(codigoState)) {
            val response = resetPasswordRepository.validateToken(email, codigoState)

            if (response.isSuccessful) {
                viewModel.id = response.body()?.get("id")?.asInt
                viewModel.email = ""

                onLoading(false)
                navController.navigate("change_password")
            } else {
                val erroBody = response.errorBody()?.string()

                onLoading(false)
                Toast.makeText(context, "Token digitado é inválido", Toast.LENGTH_LONG).show()
                Log.e("reset", "reset: $erroBody")
            }
        } else {

            onLoading(false)
            Toast.makeText(context, "Token digitado é inválido", Toast.LENGTH_LONG).show()
            Log.e("reset", "reset")
        }
    }
}

fun insertCodeValidation(codigo: Int): Boolean {
    return (codigo.toString().length == 4)
}