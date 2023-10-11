package br.senai.sp.jandira.s_book

import android.content.Context
import android.content.SharedPreferences

class Storage {

    fun salvarValorString(context: Context, valor: String, nome: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(nome, valor)
        editor.apply()
    }

    fun lerValorString(context: Context, nome: String): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)
        return sharedPreferences.getString(nome, null)
    }

}