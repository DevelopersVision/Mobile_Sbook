package br.senai.sp.jandira.s_book.components.anuncio.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.TipoAnuncio

@Composable
fun BoxAnuncio(
    nomeAnuncio: String,
    listaGeneros: List<Genero>,
    tipoAnuncio: List<TipoAnuncio>,
    fotoUser: String,
    nomeUser: String,
    cidade: String,
    estado: String
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Diário de um Banana",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF404040)
                        )
                )
            }
        }
    }
}