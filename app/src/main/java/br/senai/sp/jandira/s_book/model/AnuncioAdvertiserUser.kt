package br.senai.sp.jandira.s_book.model

import br.senai.sp.jandira.s_book.model.chat.UserChat

data class AnuncioAdvertiserUser(
    val id: Int,
    var nome: String,
    var ano_lancamento: Int,
    val data_criacao: String,
    val status_anuncio: Boolean,
    var edicao: String,
    val preco: Double?,
    var descricao: String,
    var numero_paginas: Int,
    val anunciante: Long,
)
