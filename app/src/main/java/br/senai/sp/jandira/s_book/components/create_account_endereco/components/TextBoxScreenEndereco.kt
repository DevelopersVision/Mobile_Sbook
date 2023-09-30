package br.senai.sp.jandira.s_book.components.create_account_endereco.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBoxScreenEndereco(label : String, valor: String) {

    OutlinedTextField(
        value = valor,
        onValueChange = {},
        readOnly = true,
        label = {
            Text(
                text = label,
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





//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun  TextBoxScreenPreview() {
//    TextBoxScreen(label = "oi", valor = "12", aoMudar = )
//}