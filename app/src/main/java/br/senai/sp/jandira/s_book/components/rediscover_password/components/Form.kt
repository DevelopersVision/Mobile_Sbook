package br.senai.sp.jandira.s_book.components.rediscover_password.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.components.universal.TextFieldPasswordScreen

@Composable
fun Form (
    passwordState: String,
    confirmPasswordState: String,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit
){
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextFieldPasswordScreen(
            label = "Nova senha",
            valor = passwordState,
            aoMudar = {
                onPasswordChange(it)
            }
        )
        TextFieldPasswordScreen(
            label = "Confirmar senha" ,
            valor = confirmPasswordState,
            aoMudar ={
                onConfirmPasswordChange(it)
            }
        )
    }

}