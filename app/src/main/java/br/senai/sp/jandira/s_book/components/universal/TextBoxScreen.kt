package br.senai.sp.jandira.s_book.components.universal

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBoxScreen(label : String, valor: String, aoMudar: (String) -> Unit ) {

    OutlinedTextField(
        value = valor,
        onValueChange = {
            aoMudar(it)
        },
        label = {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color(159, 152, 152, 255)
            )
        },
        modifier = Modifier
            .height(60.dp)
            .width(300.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = br.senai.sp.jandira.s_book.R.color.cinza ),
            unfocusedBorderColor = colorResource(id = br.senai.sp.jandira.s_book.R.color.cinza )
        )

    )
}