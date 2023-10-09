package br.senai.sp.jandira.s_book.components.EditUser.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

@Composable
fun Form(
    context: Context
){


    var nomeState by remember {
        mutableStateOf("")
    }

    var emailState by remember {
        mutableStateOf("")
    }

    var cepState by remember {
        mutableStateOf("")
    }

    var selectedDate by remember { mutableStateOf("") }

    var senhaState by remember {
        mutableStateOf("")
    }
    var redefinirsenhaState by remember {
        mutableStateOf("")
    }

    var userRating by remember { mutableStateOf(0) }


    Column() {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
            ) {
                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .align(alignment = Alignment.TopEnd),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.susanna_profile),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.shadow(
                            elevation = 4.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = null,
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .size(32.dp)
                )
            }
            br.senai.sp.jandira.s_book.components.perfil.components.RatingBar(
                maxRating = 5,
                initialRating = userRating
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Informações do Usuário",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(170, 98, 49, 255),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            TextField(
                value = nomeState,
                onValueChange = {
                    nomeState = it
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color(206, 206, 206, 255),
                    unfocusedIndicatorColor = Color(206, 206, 206, 255),
                    disabledIndicatorColor = Color(206, 206, 206, 255),
                    errorIndicatorColor = Color(206, 206, 206, 255)
                ),
                textStyle = TextStyle(
                    color = Color(69, 90, 100, 255),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                ),
                label = {
                    Text(
                        text = "Nome",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF808080)
                    )
                }
            )
            TextField(
                value = emailState,
                onValueChange = {
                    emailState = it
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color(206, 206, 206, 255),
                    unfocusedIndicatorColor = Color(206, 206, 206, 255),
                    disabledIndicatorColor = Color(206, 206, 206, 255),
                    errorIndicatorColor = Color(206, 206, 206, 255)
                ),
                textStyle = TextStyle(
                    color = Color(69, 90, 100, 255),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                ),
                label = {
                    Text(
                        text = "Email",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF808080)
                    )
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = cepState,
                    onValueChange = {
                        cepState = it
                    },
                    modifier = Modifier.width(140.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color(206, 206, 206, 255),
                        unfocusedIndicatorColor = Color(206, 206, 206, 255),
                        disabledIndicatorColor = Color(206, 206, 206, 255),
                        errorIndicatorColor = Color(206, 206, 206, 255)
                    ),
                    textStyle = TextStyle(
                        color = Color(69, 90, 100, 255),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500)
                    ),
                    label = {
                        Text(
                            text = "CEP",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF808080)
                        )
                    }
                )
                caixa(
                    context,
                    selectedDate,
                    onDateChange = {selectedDate = it}
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Endereço",
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            color = Color(170, 98, 49, 255),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.endereco),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
            Column() {
                Text(
                    text = "Rua Odilon Henrique de Macedo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF455A64),
                )
                Text(
                    text = "Carapicuíba, SP",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF808080),
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color(206, 206, 206, 255)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ) {
            Text(
                text = "Categorias",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(170, 98, 49, 255),
            )
            Text(
                text = "adicionar mais",
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF9F9898),
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Card(
                modifier = Modifier
                    .height(30.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFAA6231),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp, 0.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Tecnologia e Ciência",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600)
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Card(
                modifier = Modifier
                    .height(30.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFAA6231),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp, 0.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tecnologia e Ciência",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600)
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }
    }
}