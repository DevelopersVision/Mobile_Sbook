package br.senai.sp.jandira.s_book.Components.UniversalScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun  TextContinueScreen(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(

            modifier = Modifier
                . fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box (
                modifier = Modifier
                    .height(2.dp)
                    .width(100.dp)
                    .background(Color(128, 128, 128)),
                ){}

            Text(text = "Ou continue com")

            Box (
                modifier = Modifier
                    .height(2.dp)
                    .width(100.dp)
                    .background(Color(128, 128, 128)),
            ){}
        }
    }

}

