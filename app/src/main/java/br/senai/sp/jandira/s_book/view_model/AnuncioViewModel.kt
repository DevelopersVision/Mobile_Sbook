package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.Editora
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.Idioma
import br.senai.sp.jandira.s_book.model.TipoAnuncio

class AnuncioViewMODEL: ViewModel() {
    var foto: List<Foto>? = null
    var nome: String? = ""
    var generos: List<Genero>? = null
    var anunciante_foto: String? = ""
    var anunciante_nome: String? = ""
    var cidade_anuncio: String = ""
    var estado_anuncio: String = ""
    var descricao: String = ""
    var tipo_anuncio: List<TipoAnuncio>? = null
    var ano_edicao: Int = 0
    var autor: List<Autores>? = null
    var editora: Editora? = null
    var idioma: Idioma? = null
}

