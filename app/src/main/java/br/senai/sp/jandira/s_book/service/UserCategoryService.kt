package br.senai.sp.jandira.s_book.service

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserCategoryService {
    @Headers("Content-Type: application/json")
    @POST("/v1/sbook/generos-preferidos")
    suspend fun inserirCategoriasdoUsuario(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @PUT("/v1/sbook/generos-preferidos")
    suspend fun atualizarGenerosUsuario(@Body body: JsonObject): Response<JsonObject>
}