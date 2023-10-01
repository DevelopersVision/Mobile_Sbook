package br.senai.sp.jandira.s_book.model

data class JsonFavoritados(
    val anuncio: Anuncio,
    val idioma: Idioma,
    val endereco: Endereco,
    val estado_livro: EstadoLivro,
    val editora: Editora,
    val foto: List<Foto>,
    val generos: List<Genero>,
    val tipo_anuncio: List<TipoAnuncio>,
    val autores: List<Autores>
)
