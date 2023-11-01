package br.senai.sp.jandira.s_book.model


data class AnuncioNoPageBaseResponse(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val anuncios: List<JsonAnuncios>
)