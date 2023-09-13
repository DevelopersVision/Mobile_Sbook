package br.senai.sp.jandira.s_book.Components.addressScreen.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.s_book.Components.addressScreen.Components.Form
import br.senai.sp.jandira.s_book.Components.addressScreen.Components.Header
import br.senai.sp.jandira.s_book.Components.UniversalScreen.GoogleScreen
import br.senai.sp.jandira.s_book.Components.UniversalScreen.TextContinueScreen

@Preview(showSystemUi = true)
@Composable
fun AddressScreen(){
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            )
        {
            Header()
            Form()
            TextContinueScreen()
            GoogleScreen()
        }
    }
}