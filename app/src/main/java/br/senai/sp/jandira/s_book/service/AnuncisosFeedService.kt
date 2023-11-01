package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.AnuncioNoPageBaseResponse
import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.AnunciosFavoritosBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnuncisosFeedService {
    @GET("v1/sbook/anuncio")
    fun getAnuncios(@Query("page") page: Int): Call<AnunciosBaseResponse>



}