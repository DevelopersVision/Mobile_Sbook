package br.senai.sp.jandira.s_book.Components.RediscoverPasswordScreen.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.Components.UniversalScreen.TextFildPasswordScreen

@Preview(showSystemUi = true)
@Composable
fun Form (){
    
    var novasenhaState by remember {
        mutableStateOf("")
    }

    var confirmarsenhaState by remember {
        mutableStateOf("")
    }


    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextFildPasswordScreen(
            label = "Nova senha",
            valor = novasenhaState,
            aoMudar = {
                novasenhaState = it
            }
        )
        TextFildPasswordScreen(
            label = "Confirmar senha" ,
            valor = confirmarsenhaState,
            aoMudar ={
                confirmarsenhaState = it
            }
        )
    }

}