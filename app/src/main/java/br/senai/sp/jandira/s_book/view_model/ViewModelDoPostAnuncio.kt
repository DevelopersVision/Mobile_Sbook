package br.senai.sp.jandira.s_book.view_model

import android.net.Uri
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.AutoresParaPostAnuncio

class ViewModelDoPostAnuncio: ViewModel() {
    var nomedolivro: String? = ""
    var sinopse: String? = ""
    var idiomaDolivro: String? = ""
    var editoraDoLivro: String? = ""
    var numeroDePaginas: Int? = 0
    var anoDeLancamento: String? = ""
    var edicaoLivro: String = ""
    var isbn: String? = ""
    var tipoDoAnuncio: String? = ""
    var imagensJaselecionadas: List<Uri>? = null
    var imagensCertas: String = ""

}