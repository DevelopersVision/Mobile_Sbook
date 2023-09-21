package br.senai.sp.jandira.s_book.model

import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import org.json.JSONObject

class UserCategoryViewModel : ViewModel() {

    var id_usuario: Int = 0
    var generos_preferidos: List<Genero>? = null

}