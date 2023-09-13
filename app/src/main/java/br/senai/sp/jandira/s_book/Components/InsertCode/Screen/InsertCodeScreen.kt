package br.senai.sp.jandira.s_book.Components.InsertCode.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.s_book.Components.InsertCode.Components.ButtonCode
import br.senai.sp.jandira.s_book.Components.InsertCode.Components.Form
import br.senai.sp.jandira.s_book.Components.InsertCode.Components.Header
import br.senai.sp.jandira.s_book.Components.InsertCode.Components.TextFildCode

@Preview(showSystemUi = true)
@Composable
fun InsertCode(){
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
          Form()
      }
  }
}