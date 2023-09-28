package br.senai.sp.jandira.s_book.model

data class JsonFavoritados(
    val anuncio: Anuncio,
    val idioma: Idioma,
    val endereco: Endereco,
    val estado_livro: EstadoLivro,
    val editora: Editora,
    val foto: Foto,
    val generos: Genero,
    val tipo_anuncio: TipoAnuncio,
    val autores: Autores
)
