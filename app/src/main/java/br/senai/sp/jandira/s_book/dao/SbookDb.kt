package br.senai.sp.jandira.s_book.dao

import androidx.room.Database
import br.senai.sp.jandira.s_book.models_private.User

@Database(entities = [User::class], version = 1)
abstract class SbookDb {



}