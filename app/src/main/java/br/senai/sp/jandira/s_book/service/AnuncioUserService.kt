package br.senai.sp.jandira.s_book.service


import br.senai.sp.jandira.s_book.model.AnuncioNoPageBaseResponse
import br.senai.sp.jandira.s_book.model.AnunciosUserBaseResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface AnuncioUserService {

    @GET("/v1/sbook/anuncio-usuario/{id}")
    fun getAnunciosByUsuarioId(@Path("id") id: Long): Call<AnuncioNoPageBaseResponse>

    @DELETE("/v1/sbook/anuncio-delete/{id}")
    fun deleteAnuncioById(@Path("id") id: Long): Call<JsonObject>

}