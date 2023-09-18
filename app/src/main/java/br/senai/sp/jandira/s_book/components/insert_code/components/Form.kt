package br.senai.sp.jandira.s_book.components.insert_code.components

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.model.ResetPasswordView
import br.senai.sp.jandira.s_book.repository.ResetPasswordRepository
import kotlinx.coroutines.launch


@Composable
fun Form(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ResetPasswordView
){
    var codigoState by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),

            ) {
                TextFieldCode(
                    "Codigo",
                    codigoState,
                    {
                        codigoState = it
                    }
                    )
        }
        Spacer(modifier = Modifier.height(64.dp))
        ButtonCode(
            text = "Continuar",
            onClick = {
                val resetPasswordRepository = ResetPasswordRepository()

                lifecycleScope.launch {
                    //var codigoFull = "$codigoState" + "$codigo2State" + "$codigo3State" + "$codigo4State"
                    val id = viewModel.id

                    val response = resetPasswordRepository.validateToken(id, codigoState.toInt())

                    if (response.isSuccessful) {
                        Log.e("reset", "reset: ${response.body()}")
                    } else {
                        val erroBody = response.errorBody()?.string()

                        Log.e("reset", "reset: $erroBody")
                    }
                }



            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        DefaultButtonScreen(
            text = "Reenviar código"
        ) {}
    }
}