package br.senai.sp.jandira.s_book.model

data class ViaCep (
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val localidade: String,
    val uf: String
)