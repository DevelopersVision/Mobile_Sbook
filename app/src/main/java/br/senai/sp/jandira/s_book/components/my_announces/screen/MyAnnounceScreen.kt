package br.senai.sp.jandira.s_book.components.my_announces.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.insert_code.components.Form
import br.senai.sp.jandira.s_book.components.insert_code.components.Header
import br.senai.sp.jandira.s_book.components.my_announces.components.HeaderAnnounce
import br.senai.sp.jandira.s_book.view_model.ResetPasswordView

@Composable
fun MyAnnounceScreen(
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope
){
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            HeaderAnnounce()
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

            }
        }
    }
}