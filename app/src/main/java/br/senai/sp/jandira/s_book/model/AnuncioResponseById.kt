package br.senai.sp.jandira.s_book.model

data class AnuncioResponseById(
    val status: Int,
    val quantidade: Int,
    val anuncios: JsonAnuncios
)