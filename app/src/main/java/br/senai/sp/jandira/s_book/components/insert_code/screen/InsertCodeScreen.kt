package br.senai.sp.jandira.s_book.components.insert_code.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.insert_code.components.Form
import br.senai.sp.jandira.s_book.components.insert_code.components.Header
import br.senai.sp.jandira.s_book.view_model.ResetPasswordView

@Composable
fun InsertCode(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ResetPasswordView
){
  Surface(
      modifier = Modifier
          .fillMaxSize()
  ) {
      Column(
          modifier = Modifier
              .fillMaxWidth()
              .fillMaxHeight()
      ) {
          Header()
          Form(
              navController,
              lifecycleScope,
              viewModel
          )
      }
  }
}