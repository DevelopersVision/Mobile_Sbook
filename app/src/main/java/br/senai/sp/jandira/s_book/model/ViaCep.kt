package br.senai.sp.jandira.s_book.model

data class ViaCep (
    val cep: String,
    val street: String,
    val neighborhood: String,
    val city: String,
    val state: String
)