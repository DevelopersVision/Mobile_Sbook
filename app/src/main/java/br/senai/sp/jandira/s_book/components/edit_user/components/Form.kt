package br.senai.sp.jandira.s_book.components.edit_user.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import br.senai.sp.jandira.s_book.components.my_informations.components.BoxCEP
import br.senai.sp.jandira.s_book.components.my_informations.components.BoxDataNasicmento
import br.senai.sp.jandira.s_book.components.my_informations.components.BoxMyInformations
import br.senai.sp.jandira.s_book.components.perfil.components.converterData
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository

@Composable
fun Form(
    context: Context
){

    //var userRating by remember { mutableStateOf(0) }

    val dadaUser = UserRepository(context).findUsers()

    var array = User()

    var data = ""

    if (dadaUser.isNotEmpty()) {
        array = dadaUser[0]

        data = converterData(array.dataNascimento)
    }

    var nomeState by remember {
        mutableStateOf(array.nome)
    }

    var emailState by remember {
        mutableStateOf(array.email)
    }

    var cepState by remember {
        mutableStateOf(array.cep)
    }

    var selectedDate by remember { mutableStateOf(data) }

    var senhaState by remember {
        mutableStateOf("")
    }
    var redefinirsenhaState by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Informações do Usuário",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(170, 98, 49, 255),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            BoxMyInformations(
                label = "Nome",
                value = nomeState,
                onValueChange = {
                    nomeState = it
                },
                readOnly = false
            )
            BoxMyInformations(
                label = "Email",
                value = emailState,
                onValueChange = {
                    emailState = it
                },
                readOnly = false
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BoxCEP(
                    label = "CEP",
                    value = cepState,
                    onValueChange = {
                        cepState = it
                    },
                    readOnly = false
                )
                BoxDataNasicmento(
                    context = context,
                    selectedDate = selectedDate,
                    onDateChange = {
                                   selectedDate = it
                    },
                    readOnly = true
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
                .fillMaxWidth()
                .clickable {
                           Toast.makeText(context, "Não é necessário mudar o endereço manualmente, mude o CEP que será atualizado", Toast.LENGTH_SHORT).show()
                },
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
    }
}