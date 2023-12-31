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
    context: Context,
    nome: String,
    email: String,
    cep: String,
    data: String
) {

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
                value = nome,
                onValueChange = {},
                readOnly = true,
                onClickable = {}
            )
            BoxMyInformations(
                label = "Email",
                value = email,
                onValueChange = {},
                readOnly = true,
                onClickable = {}
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BoxCEP(
                    label = "CEP",
                    value = cep,
                    onValueChange = {},
                    readOnly = true
                )
                BoxDataNasicmento(
                    context = context,
                    selectedDate = data,
                    onDateChange = {},
                    readOnly = false,
                    onIsPersonOver18 = {}
                )
            }
        }
    }
}