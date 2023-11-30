package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.AnuncioDonationsResponse
import br.senai.sp.jandira.s_book.model.AnuncioNoPageBaseResponse
import br.senai.sp.jandira.s_book.model.AnuncioResponseById
import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.AnunciosFavoritosBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnuncisosFeedService {
    @GET("v1/sbook/anuncio")
    fun getAnuncios(@Query("page") page: Int): Call<AnunciosBaseResponse>

    @GET("v1/sbook/anuncio-doacao")
    fun getAnuncioDoacao(@Query("page") page: Int): Call<AnuncioDonationsResponse>

    @GET("v1/sbook/anuncio/{id}")
    fun getAnuncioByID(@Path("id") id: Int): Call<AnuncioResponseById>

}