package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.AnunciosFavoritosBaseResponse
import br.senai.sp.jandira.s_book.model.DesfavoritarBaseResponse
import br.senai.sp.jandira.s_book.model.VerificarFavoritoBaseResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface AnunciosFavoritadosService {
    @Headers("Content-Type: application/json")
    @POST("/v1/sbook/favoritar-anuncio")
    suspend fun favoritarAnuncio(@Body body: JsonObject): Response<JsonObject>

    @GET("/v1/sbook/anuncios-favoritados/{id}")
    fun getAnunciosFavoritosByUsuarioId(@Path("id") id: Long): Call<AnunciosFavoritosBaseResponse>

    @Headers("Content-Type: application/json")
    @DELETE("/v1/sbook/remover-favorito/{user}/{anuncio}")
    fun destavoritarAnuncio(@Path("user") user: Long, @Path("anuncio") anuncio: Int): Call<DesfavoritarBaseResponse>

    @GET("/v1/sbook/verificar-favorito/{user}/{anuncio}")
    fun verificarFavorito(@Path("user") user: Long, @Path("anuncio") anuncio: Int): Call<VerificarFavoritoBaseResponse>

}