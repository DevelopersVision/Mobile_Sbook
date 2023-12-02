package br.senai.sp.jandira.s_book.model

import br.senai.sp.jandira.s_book.model.chat.UserChat

data class AnuncioAdvertiserUser(
    val id: Int,
    val nome: String,
    val ano_lancamento: Int,
    val edicao: String,
    val preco: Double?,
    val anunciante: Long
)
