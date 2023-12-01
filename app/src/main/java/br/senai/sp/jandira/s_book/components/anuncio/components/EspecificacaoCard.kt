package br.senai.sp.jandira.s_book.components.anuncio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.JsonAnuncios

@Composable
fun EspecificacaoCardSimples(
    text: String,
    valor: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row{
            Text(
                modifier = Modifier.width(180.dp),
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000)
                ),
                textAlign = TextAlign.Start
            )
            Text(
                text = valor,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF7E7D7A)
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.8.dp)
                .background(color = Color(0xFFE0E0E0))
        ) {}
    }
}

@Composable
fun EspecificacaoCardAutores(
    text: String,
    autores: List<Autores>
) {
    var autoresString = ""

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row{
            Text(
                modifier = Modifier.width(180.dp),
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000)
                ),
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                for (autor in autores){
                    if(autor == autores.last()){
                        autoresString+= autor.nome
                    }else{
                        autoresString+= "${autor.nome}, "
                    }
                }
                Text(
                    text = autoresString,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF7E7D7A)
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.8.dp)
                .background(color = Color(0xFFE0E0E0))
        ) {}
    }
}