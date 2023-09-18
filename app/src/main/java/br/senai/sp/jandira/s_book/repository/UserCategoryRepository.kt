package br.senai.sp.jandira.s_book.repository

import br.senai.sp.jandira.s_book.service.RetrofitHelper
import com.google.gson.JsonObject
import retrofit2.Response

class UserCategoryRepository {

    private val apiService = RetrofitHelper.userCategoryService()

    suspend fun usuarioCategoria(id_usuario: Int, generos_preferidos: Array<JsonObject>): Response<JsonObject>{

        val requestBody = JsonObject().apply {
            addProperty("id_usuario", id_usuario)
            addProperty("generos_preferidos", generos_preferidos.toString())
        }

        return apiService.inserirCategoriasdoUsuario(requestBody)
    }
}