package br.senai.sp.jandira.s_book.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.senai.sp.jandira.s_book.models_private.User

@Dao
interface UserDao {

    @Insert
    fun save(user:User): Long

    @Update
    fun update(user: User): Int

    @Delete
    fun delete(user: User): Int

    @Query("SELECT * FROM tbl_user WHERE id = :id")
    fun findUserById(id: Int): User

    @Query("SELECT * FROM tbl_user")
    fun findUsers(): User

}