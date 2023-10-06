package br.senai.sp.jandira.s_book.dao

import androidx.room.Dao
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

    @Query("DELETE FROM tbl_user WHERE id = :id")
    fun delete(id: Int): Int

    @Query("DELETE FROM tbl_user")
    fun deleteAll(): Int

    @Query("SELECT * FROM tbl_user WHERE id = :id")
    fun findUserById(id: Int): User

    @Query("SELECT * FROM tbl_user")
    fun findUsers(): List<User>

}