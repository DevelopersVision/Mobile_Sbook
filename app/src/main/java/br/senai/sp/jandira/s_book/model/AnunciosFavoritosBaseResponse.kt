package br.senai.sp.jandira.s_book.model

data class AnunciosFavoritosBaseResponse (
    val status: Int,
    val message: String,
    val quantidae: Int,
    val anuncios: List<JsonFavoritados>
)
