package br.senai.sp.jandira.s_book.repository

import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response

class UserCategoryRepository {

    private val apiService = RetrofitHelper.userCategoryService()

    suspend fun usuarioCategoria(id_usuario: Int, generos_preferidos: List<Genero>): Response<JsonObject> {

        val generosJsonArray = JsonArray()

        for (genero in generos_preferidos) {
            val generoJsonObject = JsonObject().apply {
                addProperty("id", genero.id)
                addProperty("nome", genero.nome)
            }
            generosJsonArray.add(generoJsonObject)
        }

        val requestBody = JsonObject().apply {
            addProperty("id_usuario", id_usuario)
            add("generos_preferidos", generosJsonArray)
        }

        return apiService.inserirCategoriasdoUsuario(requestBody)
    }
}