package br.senai.sp.jandira.s_book.model

data class IdiomaBaseResponse (
    val status: Int,
    val quantidade: Int,
    val idiomas: List<Idioma>
)