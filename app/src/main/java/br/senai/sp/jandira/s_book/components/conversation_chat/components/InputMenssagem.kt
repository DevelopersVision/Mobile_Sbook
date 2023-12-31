package br.senai.sp.jandira.s_book.components.conversation_chat.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import org.json.JSONObject

//@Preview(showSystemUi = true)
@Composable
fun  InputMenssagem(
    mensagem:String,
    onclick: (String) -> Unit,
    navController: NavController
){
    var mensagemState by remember {
        mutableStateOf(mensagem)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value =  mensagemState,
            onValueChange = {
                mensagemState = it
            },
            modifier = Modifier.width(280.dp),
            trailingIcon = {
                IconButton(onClick = {
                    navController.navigate("PictureScreen")
                }) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            },
            shape = RoundedCornerShape(50.dp),
            label = {
                    Text(text = "Enviar mensagem...")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.cinza),
                unfocusedBorderColor = colorResource(id = R.color.cinza)
            )
        )
        Button(
            onClick = {
                      onclick(mensagemState)
                mensagemState = ""
            },
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(Color(221, 163, 93, 255))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_send_24),
                contentDescription = ""
            )
        }
    }
}