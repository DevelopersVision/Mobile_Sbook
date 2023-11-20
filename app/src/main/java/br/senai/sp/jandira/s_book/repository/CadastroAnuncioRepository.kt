package br.senai.sp.jandira.s_book.repository

import android.net.Uri
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.AutoresParaPostAnuncio
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.TipoAnuncio
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Response

class CadastroAnuncioRepository {

    private val apiService = RetrofitHelper.postCadastroAnuncioService()

    suspend fun cadastroAnuncio(
        nome: String,
        numeroPaginas: Int,
        anoLancamento: Int,
        descricao: String,
        edicao: String,
        isbn: String,
        preco: Double,
        idUsuario: Long,
        idEstadoLivro: Int,
        idIdioma: Int,
        idEditora: Int,
        fotos: List<Uri>?,
        tiposAnuncio: List<Int>,
        generos: List<Int>,
        autores: List<AutoresParaPostAnuncio>
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {

            val fotosJsonArray = JsonArray()

            fotos?.forEach { fotoUri ->
                fotosJsonArray.add(fotoUri.toString())
            }

            val tiposAnuncioJsonArray = JsonArray()
            tiposAnuncio.forEach { tipo ->
                tiposAnuncioJsonArray.add(tipo)
            }

            val generosJsonArray = JsonArray()
            generos.forEach { genero ->
                generosJsonArray.add(genero)
            }

            val autoresJsonArray = JsonArray()

            autores.forEach { autor ->
                val autoresJsonObject = JsonObject().apply {
                    addProperty("status_autor", autor.status_autor)
                    addProperty("id_autor",autor.id_autor)
                }
                autoresJsonArray.add(autoresJsonObject)
            }


            addProperty("nome", nome)
            addProperty("numero_paginas", numeroPaginas)
            addProperty("ano_lancamento", anoLancamento)
            addProperty("descricao", descricao)
            addProperty("edicao", edicao)
            addProperty("isbn", isbn)
            addProperty("preco", preco)
            addProperty("id_usuario", idUsuario)
            addProperty("id_estado_livro", idEstadoLivro)
            addProperty("id_idioma", idIdioma)

            addProperty("id_editora", idEditora)
            add("fotos", fotosJsonArray)
            add("tipos_anuncio",tiposAnuncioJsonArray)
            add("generos", generosJsonArray)
            add("autores", autoresJsonArray)
        }

        return apiService.cadastroAnuncio(requestBody)
    }

}