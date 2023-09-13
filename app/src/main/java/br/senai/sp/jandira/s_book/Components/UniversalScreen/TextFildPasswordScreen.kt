package br.senai.sp.jandira.s_book.Components.UniversalScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFildPasswordScreen(label : String, valor: String, aoMudar: (String) -> Unit ){

//    val  versenha = mutableListOf<>()

    OutlinedTextField(
        value = valor,
        onValueChange = {
            aoMudar(it)
        },
        label={
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                color = Color(159,152,152,)
            )
        },
        modifier = Modifier
            .height(60.dp)
            .width(300.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.cinza ),
            unfocusedBorderColor = colorResource(id = R.color.cinza )
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
}


//modifier = Modifier
//            .border(width = 0.8.dp,
//        color = Color(0xFFCECECE),
//        )
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//        leadingIcon = {
//            Icon(
//                painter = painterResource(
//                    id = R.drawable.baseline_lock_24
//                ),
//                contentDescription = "",
//                tint = colorResource(id = R.color.pink_login)
//            )
//        }