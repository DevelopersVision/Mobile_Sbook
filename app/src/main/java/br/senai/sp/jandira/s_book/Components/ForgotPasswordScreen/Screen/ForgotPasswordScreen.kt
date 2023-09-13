package br.senai.sp.jandira.s_book.Components.ForgotPasswordScreen.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.Components.ForgotPasswordScreen.Components.Footer
import br.senai.sp.jandira.s_book.Components.ForgotPasswordScreen.Components.Form
import br.senai.sp.jandira.s_book.Components.ForgotPasswordScreen.Components.Header

@Preview(showSystemUi = true)
@Composable
fun ForgotPasswordScreen(){
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
            Footer()
        }

    }
}