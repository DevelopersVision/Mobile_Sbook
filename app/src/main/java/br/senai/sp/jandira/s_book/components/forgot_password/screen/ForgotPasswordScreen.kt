package br.senai.sp.jandira.s_book.components.forgot_password.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.forgot_password.components.Footer
import br.senai.sp.jandira.s_book.components.forgot_password.components.Form
import br.senai.sp.jandira.s_book.components.forgot_password.components.Header
import br.senai.sp.jandira.s_book.view_model.ResetPasswordView

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ResetPasswordView
){

    var emailState by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Header()
            Spacer(modifier = Modifier.height(64.dp))
            Form(emailState, onEmailChange = { emailState = it })
            Spacer(modifier = Modifier.height(72.dp))
            Footer(navController, lifecycleScope, viewModel, emailState)
        }

    }
}