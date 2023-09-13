package br.senai.sp.jandira.s_book.components.rediscover_password.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.components.rediscover_password.components.Form
import br.senai.sp.jandira.s_book.components.rediscover_password.components.Header
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen

@Preview(showSystemUi = true)
@Composable
fun RediscoverPasswordScreen(){
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
            Header()
            Form()
            Spacer(modifier = Modifier.height(30.dp))
            DefaultButtonScreen(text = "Redefinir Senha") {

            }
        }

    }
}