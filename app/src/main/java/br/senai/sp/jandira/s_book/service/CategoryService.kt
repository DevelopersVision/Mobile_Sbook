package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.repository.CategoryList
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {
    @GET("/v1/sbook/generos")
    fun getGeneros(): Call<CategoryList>
}