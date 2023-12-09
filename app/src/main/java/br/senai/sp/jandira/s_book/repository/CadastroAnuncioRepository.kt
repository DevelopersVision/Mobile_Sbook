package br.senai.sp.jandira.s_book.repository

import android.util.Log
import br.senai.sp.jandira.s_book.model.AutoresParaPostAnuncio
import br.senai.sp.jandira.s_book.model.JsonAnuncios
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
        fotos: List<String>?,
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
                    addProperty("id_autor", autor.id_autor)
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
            add("tipos_anuncio", tiposAnuncioJsonArray)
            add("generos", generosJsonArray)
            add("autores", autoresJsonArray)
        }

        return apiService.cadastroAnuncio(requestBody)
    }

    suspend fun updateAnnounce(
        dadosAnuncio: JsonAnuncios
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {

            val fotosJsonArray = JsonArray()

            dadosAnuncio.foto.forEach { fotoUri ->
                fotosJsonArray.add(fotoUri.foto)
            }

            val tiposAnuncioJsonArray = JsonArray()

            if (dadosAnuncio.tipo_anuncio[0].id == 1) {
                tiposAnuncioJsonArray.add(1)
            } else {
                dadosAnuncio.tipo_anuncio.forEach { tipo ->
                    tiposAnuncioJsonArray.add(tipo.id)
                }
            }


            val generosJsonArray = JsonArray()
            dadosAnuncio.generos.forEach { genero ->
                generosJsonArray.add(genero.id)
            }

            val autoresJsonArray = JsonArray()

            dadosAnuncio.autores.forEach { autor ->
                autoresJsonArray.add(autor.id)
            }


            addProperty("id_anuncio", dadosAnuncio.anuncio.id)
            addProperty("nome", dadosAnuncio.anuncio.nome)
            addProperty("numero_paginas", dadosAnuncio.anuncio.numero_paginas)
            addProperty("ano_lancamento", dadosAnuncio.anuncio.ano_lancamento)
            addProperty("descricao", dadosAnuncio.anuncio.descricao)
            addProperty("edicao", dadosAnuncio.anuncio.edicao)
            addProperty("isbn", dadosAnuncio.anuncio.isbn)
            addProperty("preco", dadosAnuncio.anuncio.preco)
            addProperty("id_estado_livro", dadosAnuncio.estado_livro.id)
            addProperty("id_idioma", dadosAnuncio.idioma.id)

            addProperty("id_editora", dadosAnuncio.editora.id)
            add("fotos", fotosJsonArray)
            add("tipos_anuncio", tiposAnuncioJsonArray)
            add("generos", generosJsonArray)
            add("autores", autoresJsonArray)
        }

        Log.w("BODY", "Update: $requestBody")

        return apiService.updateAnuncio(requestBody)
    }
}