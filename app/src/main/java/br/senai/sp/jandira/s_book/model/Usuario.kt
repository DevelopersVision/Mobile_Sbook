package br.senai.sp.jandira.s_book.model

data class Usuario(
    val id_usuario: Long,
    val nome: String,
    val data_nascimento: String,
    val data_criacao: String,
    val email: String,
    val cpf: String,
    val status_usuario: Boolean,
    val foto: String,
    val logradouro: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val cep: String,
    val id_endereco: Int
)
