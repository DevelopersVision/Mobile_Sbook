package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.Editora
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.Idioma
import br.senai.sp.jandira.s_book.model.TipoAnuncio

class AnuncioViewModel: ViewModel() {
    var id: Int? = 0
    var foto: List<Foto> = listOf<Foto>()
    var nome: String? = ""
    var generos: List<Genero> = listOf()
    var anunciante_foto: String? = ""
    var anunciante_nome: String? = ""
    var cidade_anuncio: String = ""
    var estado_anuncio: String = ""
    var descricao: String = ""
    var tipo_anuncio: List<TipoAnuncio> = listOf()
    var ano_edicao: Int = 0
    var autor: List<Autores> = listOf()
    var editora: Editora? = null
    var idioma: Idioma? = null
    var preco: Double? = null
}
