package br.senai.sp.jandira.s_book.model

data class EditoraBaseResponse (
    val status: Int,
    val quantidade: Int,
    val editoras: List<Editora>
)