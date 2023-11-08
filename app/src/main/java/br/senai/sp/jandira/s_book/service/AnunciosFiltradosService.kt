package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnunciosFiltradosService {

    @GET("/v1/sbook/anuncios-filtros")
    fun getAnunciosFiltrados(
        @Query("array_generos") arrayGeneros: MutableList<String>?,
        @Query("array_estado_livro") arrayEstadoLivro: MutableList<String>?

    ): Call<AnunciosBaseResponse>
}