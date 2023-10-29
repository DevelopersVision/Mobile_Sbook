package br.senai.sp.jandira.s_book.model

data class TipoAnuncioBaseResponse(
    val status: Int,
    val quantidade: Int,
    val tipos: List<TipoAnuncio>
)
