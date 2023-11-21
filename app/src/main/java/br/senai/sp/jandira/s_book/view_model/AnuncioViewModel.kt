package br.senai.sp.jandira.s_book.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.s_book.model.AnuncioNoPageBaseResponse
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.Editora
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.Idioma
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.model.TipoAnuncio
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class AnuncioViewModel: ViewModel() {
    var id_anunciante: Long? = 0
    var foto: List<Foto> = listOf<Foto>()
//    var nome: String? = ""
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


    // Exemplo de atributos, ajuste conforme sua estrutura real
    var id by mutableStateOf(0)
    var nome by mutableStateOf("")
    // ... outros atributos ...

    // Método para recarregar dados específicos da tela
    fun recarregarDadosEspecificos() {
        // Lógica para recarregar dados específicos da tela
        // Você pode usar os atributos existentes da ViewModel para construir a lógica necessária
        viewModelScope.launch {
            // Exemplo: Simulando uma chamada de API para recarregar dados específicos
            delay(1000) // Aguardar 1 segundo (substitua por sua lógica real)

            // Atualizar os atributos conforme necessário
            id = 1
            nome = "Novo Nome"
            // ... outros atributos ...

            // Notificar os observadores sobre as mudanças nos dados
            notifyDataChanged()
        }
    }

    // Método auxiliar para notificar os observadores sobre as mudanças nos dados
    private fun notifyDataChanged() {
        // Usar o método setValue do LiveData para notificar os observadores sobre as mudanças
        // Aqui estou usando MutableState, ajuste conforme sua implementação real
        id = id
        nome = nome
        // ... notificar outros atributos ...
    }

}



