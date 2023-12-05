package br.senai.sp.jandira.s_book.model

data class AnuncioResponse(
    var anuncio: Anuncio,
    var idioma: Idioma,
    val endereco: Endereco,
    val estado_livro: EstadoLivro,
    var editora: Editora,
    val foto: List<Foto>,
    val generos: List<Genero>,
    val tipo_anuncio: List<TipoAnuncio>,
    val autores: List<Autores>,
)
