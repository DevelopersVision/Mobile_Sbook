package br.senai.sp.jandira.s_book.model

data class DadosAdvertiser(
    val id_usuario: Int,
    val nome: String,
    val email: String,
    val foto: String,
    val cidade: String,
    val estado: String,
    val generos: List<GenerosAdvertiser>,
    val anuncios: List<AnuncioAdvertiser>
)
