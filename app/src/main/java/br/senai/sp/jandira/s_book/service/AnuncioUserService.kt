package br.senai.sp.jandira.s_book.service


import br.senai.sp.jandira.s_book.model.AnunciosUserBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnuncioUserService {

    @GET("/v1/sbook/anuncio-usuario/{id}")
    fun getAnunciosByUsuarioId(@Path("id") id: Long): Call<AnunciosUserBaseResponse>

}