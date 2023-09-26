package br.senai.sp.jandira.s_book.model

data class Anuncio(
    val id_anunciado: Int,
    val favoritados: String,
    val ano_lancamento: Int,
    val data_criacao: String,
    val status_anuncio: Boolean,
    val edicao: String,
    val preco: String,
    val descricao: String,
    val numero_paginas: Int,
    val anunciante: Int,
    val estado: String,
    val cidade: String,
    val bairro: String,
    val estado_livro: String,
    val idioma: String,
    val id_editora: Int,
    val editora: String,
    val foto: String
)
