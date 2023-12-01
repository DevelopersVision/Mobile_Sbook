package br.senai.sp.jandira.s_book.components.announce.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import br.senai.sp.jandira.s_book.model.JsonAnuncios

@Composable
fun EspecificacoesAnuncioBox(dadosAnuncios: JsonAnuncios) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(2.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Especificações",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000)
            )
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            EspecificacaoCardSimples(text = "Ano da edição", valor = dadosAnuncios.anuncio.ano_lancamento.toString())
            EspecificacaoCardSimples(text = "Editora", valor = dadosAnuncios.editora.nome)
            EspecificacaoCardAutores(text = "Autores", autores = dadosAnuncios.autores)
            EspecificacaoCardSimples(text = "Idioma", valor = dadosAnuncios.idioma.nome)
            EspecificacaoCardSimples(text = "Estado do livro", valor = dadosAnuncios.estado_livro.estado)
            EspecificacaoCardSimples(text = "Edição", valor = dadosAnuncios.anuncio.edicao)
            EspecificacaoCardSimples(text = "Numero de páginas", valor = dadosAnuncios.anuncio.numero_paginas.toString())
        }
    }
}