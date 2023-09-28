package br.senai.sp.jandira.s_book.components.cep.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.components.cep.components.Form
import br.senai.sp.jandira.s_book.components.cep.components.Header
import br.senai.sp.jandira.s_book.components.create_account.components.Button

@Preview(showSystemUi = true)
@Composable
fun CepScreen(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            Form()
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                text = "Continuar"
            ) {

            }
        }
    }
}