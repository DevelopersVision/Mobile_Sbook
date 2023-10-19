package br.senai.sp.jandira.s_book.components.my_informations.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.perfil.components.converterData
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository

@Composable
fun UserInformations(
    context: Context
) {

    val dadaUser = UserRepository(context).findUsers()

    var array = User()

    var data = ""

    if (dadaUser.isNotEmpty()) {
        array = dadaUser[0]

        data = converterData(array.dataNascimento)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Informações do Usuário",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFFAA6231)
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            BoxMyInformations(
                label = "Nome",
                value = array.nome,
                onValueChange = {},
                readOnly = true
            )
            BoxMyInformations(
                label = "Email",
                value = array.email,
                onValueChange = {},
                readOnly = true
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BoxCEP(
                    label = "CEP",
                    value = array.cep,
                    onValueChange = {},
                    readOnly = true
                )
                BoxDataNasicmento(
                    context = context,
                    selectedDate = data,
                    onDateChange = {},
                    readOnly = false
                )
            }
        }
    }
}