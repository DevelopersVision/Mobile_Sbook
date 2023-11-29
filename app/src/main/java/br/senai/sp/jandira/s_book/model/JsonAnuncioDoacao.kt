package br.senai.sp.jandira.s_book.model

data class JsonAnuncioDoacao (
    val anuncio: Anuncio,
    val foto: List<Foto>,
    val autores: List<Autores>
)