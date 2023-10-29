package br.senai.sp.jandira.s_book.model

class EstadoLivroBaseResponse (
    val status: Int,
    val quantidade: Int,
    val estados: List<EstadoLivro>
)