package br.senai.sp.jandira.s_book.components.announceDetail.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun Imagem(
    painter: Painter,
    descricao: String,
    modifier: Modifier
): Unit{
    Image(
        painter = painter,
        contentDescription = descricao,
        modifier = modifier
    )

}