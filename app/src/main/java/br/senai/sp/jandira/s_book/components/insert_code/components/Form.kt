package br.senai.sp.jandira.s_book.components.insert_code.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen

@Preview(showSystemUi = true)
@Composable
fun Form(){

    var codigoState by remember {
        mutableStateOf("")
    }

    var codigo2State by remember {
        mutableStateOf("")
    }

    var codigo3State by remember {
        mutableStateOf("")
    }

    var codigo4State by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),

            ) {
            TextFieldCode(
                label = "",
                valor = codigoState,
                aoMudar ={
                    codigoState = it
                }
            )
            TextFieldCode(
                label = "",
                valor = codigo2State,
                aoMudar ={
                    codigo2State = it
                }
            )
            TextFieldCode(
                label = "",
                valor = codigo3State,
                aoMudar ={
                    codigo3State = it
                }
            )
            TextFieldCode(
                label = "",
                valor = codigo4State,
                aoMudar ={
                    codigo4State = it
                }
            )
        }
        Spacer(modifier = Modifier.height(64.dp))
        ButtonCode(
            text = "Continuar"
        ) {
        }
        Spacer(modifier = Modifier.height(24.dp))
        DefaultButtonScreen(
            text = "Reenviar código"
        ) {
        }
    }

}