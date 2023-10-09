package br.senai.sp.jandira.s_book.components.universal

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPasswordScreen(label : String, valor: String, aoMudar: (String) -> Unit ){

    var passwordVisibilityState by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = valor,
        onValueChange = { aoMudar(it) },
        modifier = Modifier
            .height(60.dp)
            .width(300.dp),
        shape = RoundedCornerShape(4.dp),
        visualTransformation =
        if(!passwordVisibilityState){
            PasswordVisualTransformation()
        }else{
            VisualTransformation.None
        },
        label = {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color(159, 152, 152, 255)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.cinza ),
            unfocusedBorderColor = colorResource(id = R.color.cinza )
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisibilityState = !passwordVisibilityState
                }
            ) {
                Icon(
                    imageVector =
                    if(passwordVisibilityState){
                        Icons.Default.Visibility
                    } else {
                        Icons.Default.VisibilityOff
                    },
                    contentDescription = null,
                    tint = Color(0xFFA7A5A4)

                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldPasswordPreview() {
    TextFieldPasswordScreen( label = "Senha", valor = "", aoMudar = {})
}