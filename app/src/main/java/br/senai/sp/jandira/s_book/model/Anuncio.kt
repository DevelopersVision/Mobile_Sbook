package br.senai.sp.jandira.s_book.model

data class Anuncio(
    val id: Int,
    val nome: String,
    val ano_lancamento: Int,
    val data_criacao: String,
    val status_anuncio: Boolean,
    val edicao: String,
    val preco: Double?,
    val descricao: String,
    val numero_paginas: Int,
    val anunciante: Long

)