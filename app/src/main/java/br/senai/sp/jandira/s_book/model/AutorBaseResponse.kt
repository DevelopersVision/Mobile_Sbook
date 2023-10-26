package br.senai.sp.jandira.s_book.model

data class AutorBaseResponse (
    val status: Int,
    val quantidade: Int,
    val autores: List<Autores>
)