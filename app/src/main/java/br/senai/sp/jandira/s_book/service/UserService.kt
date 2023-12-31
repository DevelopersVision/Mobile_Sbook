package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.ResponseUsuario
import br.senai.sp.jandira.s_book.model.ResponseUsuarioV2
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("v1/sbook/usuario/{id}")
    fun getUsuarioById(@Path("id") id: Long): Call<ResponseUsuario>

    @GET("v2/sbook/usuario/{id}")
    fun getUsuarioByIdProfile(@Path("id") id: Long): Call<ResponseUsuarioV2>

    @Headers("Content-Type: application/json")
    @PUT("/v1/sbook/atualizar-usuario")
    suspend fun updateUserData(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @PUT("/v1/sbook/atualizar-foto-usuario")
    suspend fun updateUserPhoto(@Body body: JsonObject): Response<JsonObject>
}