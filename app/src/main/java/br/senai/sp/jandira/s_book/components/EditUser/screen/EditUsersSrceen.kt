package br.senai.sp.jandira.s_book.components.EditUser.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.EditUser.components.Form
import br.senai.sp.jandira.s_book.components.universal.ButtonProfile
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.components.universal.HeaderProfile

//@Preview(showSystemUi = true)
@Composable
fun EditUser(
    navController: NavController
){

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(24.dp)
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderProfile( onclick = { navController.navigate("profile")})
        Form(context)
        Spacer(modifier = Modifier.height(32.dp))
        ButtonProfile("Salvar", onclick = {})
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Redefinir senha",
            fontSize = 12.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF9F9898),
            textDecoration = TextDecoration.Underline,
        )
    }
}

