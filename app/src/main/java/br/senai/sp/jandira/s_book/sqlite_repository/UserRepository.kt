package br.senai.sp.jandira.s_book.sqlite_repository

import android.content.Context
import br.senai.sp.jandira.s_book.dao.SbookDb
import br.senai.sp.jandira.s_book.models_private.User

class UserRepository(context: Context) {

    private val db = SbookDb.getDatabase(context)

    fun save(user: User): Long {
        return db.userDao().save(user)
    }

    fun update(user: User): Long{
        return db.userDao().update(user).toLong()
    }

    fun updateIdChat(idChat: String): Int{
        return db.userDao().updateIdChat(idChat)
    }

    fun findIdChat(idChat: String): User{
        return db.userDao().findIdChat(idChat)
    }

    fun findUserByEmail(id: Int): User{
        return db.userDao().findUserById(id)
    }

    fun findUsers(): List<User> {
        return db.userDao().findUsers()
    }

    fun deleteUser(id: Int): Int{
        return  db.userDao().delete(id)
    }

    fun deleteAllUser(): Int{
        return  db.userDao().deleteAll()
    }

}