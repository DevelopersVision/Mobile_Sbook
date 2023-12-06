package br.senai.sp.jandira.s_book.model

data class AnuncioAdvertiser(
    var anuncios:  AnuncioResponse,
    var idioma: Idioma,
    var endereco: Endereco,
    var estado_livro: EstadoLivro,
    var editora: Editora,
    var foto: List<Foto>,
    var generos: List<Genero>,
    var tipo_anuncio: List<TipoAnuncio>,
    var autores: List<Autores>,
)

