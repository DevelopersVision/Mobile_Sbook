package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.AnunciosFavoritosBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnuncisosFeedService {
    @GET("v1/sbook/anuncio")
    fun getAnuncios(): Call<AnunciosBaseResponse>
}