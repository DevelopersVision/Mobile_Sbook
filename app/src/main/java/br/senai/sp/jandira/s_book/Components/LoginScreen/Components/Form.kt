package br.senai.sp.jandira.s_book.Components.LoginScreen.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.Components.UniversalScreen.TextBoxScreen
import br.senai.sp.jandira.s_book.Components.UniversalScreen.TextFildPasswordScreen

@Composable
fun Form(){

    var emailState by remember {
        mutableStateOf("")
    }

    var senhaState by remember {
        mutableStateOf("")
    }


        Column (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            TextBoxScreen(
                label = "Email",
                valor = emailState,
                aoMudar = {
                    emailState = it
                }
            )
            TextFildPasswordScreen(
                label = "Senha" ,
                valor = senhaState,
                aoMudar ={
                    senhaState = it
                }
            )

            Text(
                text = "Esqueci a senha",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 45.dp),
                color = Color(159,152,152,),
                textAlign = TextAlign.End
            )
        }

}

@Preview(showSystemUi = true)
@Composable
fun FormPreview() {
    Form()
}