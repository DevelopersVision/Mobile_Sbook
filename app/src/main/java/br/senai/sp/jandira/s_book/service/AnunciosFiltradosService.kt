package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.AnunciosFavoritosBaseResponse
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.Genero
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnunciosFiltradosService {

    @GET("/v1/sbook/anuncios-filtros")
    fun getAnunciosFiltrados(
        @Query("array_generos") arrayGeneros: Array<String>?,
        @Query("array_estado_livro") arrayEstadoLivro: Array<String>?

    ): Call<AnunciosBaseResponse>
}