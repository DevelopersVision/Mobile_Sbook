package br.senai.sp.jandira.s_book.components.cep.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.components.universal.TextBoxScreen


@Composable
fun Form (
    cep: String, onCepChange: (String) -> Unit
){
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextBoxScreen(
            label = "cep" ,
            valor = cep,
            aoMudar = {
                onCepChange(it)
            }
        )
    }
}