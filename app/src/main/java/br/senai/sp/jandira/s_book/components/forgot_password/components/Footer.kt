package br.senai.sp.jandira.s_book.components.forgot_password.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.model.ResetPasswordView
import br.senai.sp.jandira.s_book.repository.ResetPasswordRepository
import kotlinx.coroutines.launch

@Composable
fun Footer(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ResetPasswordView,
    emailState: String
)
{

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Já tenho o código de redefinição",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF9F9898),
                textDecoration = TextDecoration.Underline,
            )
        )
        DefaultButtonScreen(
            text = "Solicitar código",
            onClick = {
                resetPassword(emailState, lifecycleScope, viewModel, navController)
            }
        )

    }
}

fun resetPassword(
    email: String,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ResetPasswordView,
    navController: NavController,
) {

    val resetRepository = ResetPasswordRepository()

    lifecycleScope.launch {
        val response = resetRepository.resetPassword(email)

        if(response.isSuccessful){
            viewModel.email = response.body()?.get("email")?.toString()

            Log.e("email", "resetPassword: ${response.body()?.get("email")?.toString()}", )

            Log.e("Body", "resetPassword: ${response.body()}", )

            navController.navigate("insert_code")
        }else{
            val erroBody = response.errorBody()?.string()

            Log.e("reset de senha", "reset de senha: $erroBody")
        }
    }

}