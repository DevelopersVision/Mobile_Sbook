package br.senai.sp.jandira.s_book.model

data class Anuncio(
    val id_anuncio: Int,
    val nome_livro: String,
    val ano_lancamento: String,
    val data_criacao: String,
    val status_anuncio: Boolean,
    val edicao: String,
    val preco: Double,
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
    val autor: String,
    val foto: String
)