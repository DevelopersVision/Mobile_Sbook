package br.senai.sp.jandira.s_book.components.edit_user.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.components.universal.ButtonProfile

@Composable
fun ButtonsEditUser(
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonProfile("Salvar", onclick = {
            onClick()
        })
        Text(
            text = "Redefinir senha",
            fontSize = 12.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF9F9898),
            textDecoration = TextDecoration.Underline,
        )
    }


}