package br.senai.sp.jandira.s_book.components.forgot_password.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import br.senai.sp.jandira.s_book.components.forgot_password.components.Footer
import br.senai.sp.jandira.s_book.components.forgot_password.components.Form
import br.senai.sp.jandira.s_book.components.forgot_password.components.Header
import br.senai.sp.jandira.s_book.repository.ResetPasswordRepository
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(lifecycleScope: LifecycleCoroutineScope){
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
            Form()
            Spacer(modifier = Modifier.height(72.dp))
            Footer(lifecycleScope)
        }

    }
}