package br.senai.sp.jandira.s_book.repository


import br.senai.sp.jandira.s_book.service.RetrofitHelper
import com.google.gson.JsonObject
import retrofit2.Response

class AnunciosFavoritadosRepository {

    private val apiService = RetrofitHelper.getAnunciosFavoritadosService()

    suspend fun inserirAnuncioAosFavoritos(id_usuario: Int, id_anuncio: Int): Response<JsonObject>{
        val requestBody = JsonObject().apply {
            addProperty("id_usuario", id_usuario)
            addProperty("id_anuncio", id_anuncio)
        }

        return apiService.favoritarAnuncio(requestBody)
    }

    suspend fun removerAnuncioDosFavoritos(id_usuario: Int, id_anuncio: Int): Response<JsonObject>{
        val requestBody = JsonObject().apply {
            addProperty("id_usuario", id_usuario)
            addProperty("id_anuncio", id_anuncio)
        }

        return apiService.destavoritarAnuncio(requestBody)
    }



}