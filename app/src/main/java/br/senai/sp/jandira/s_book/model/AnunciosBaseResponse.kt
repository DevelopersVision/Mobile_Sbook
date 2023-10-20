package br.senai.sp.jandira.s_book.model

data class AnunciosBaseResponse(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val page: Int,
    val anuncios: List<JsonAnuncios>
)
