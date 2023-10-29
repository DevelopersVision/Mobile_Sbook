package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.EditoraBaseResponse
import br.senai.sp.jandira.s_book.model.EstadoLivroBaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface EstadoLivroService {
    @GET("v1/sbook/estado-livro")
    fun getEstadoLivro(): Call<EstadoLivroBaseResponse>
}