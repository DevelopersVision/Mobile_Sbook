package br.senai.sp.jandira.s_book.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.s_book.models_private.User

@Database(entities = [User::class], version = 1)
abstract class SbookDb : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {

        private lateinit var instanceDb: SbookDb

        fun getDatabase(context: Context): SbookDb {
            // :: =  entregar uma instancia do objeto, me devolve um false se não existir nada

            //se isso for verdade
            if (!::instanceDb.isInitialized) {
                //.databaseBuilder =  criar um banco de dados
                // criar a instancia
                instanceDb = Room
                    .databaseBuilder(
                        context, //contexto da minha aplicação
                        SbookDb::class.java, //ja esta criando automaticamente a instancia
                        "db_sbook" // nome do banco
                    ).allowMainThreadQueries().build()
            }
            return instanceDb

        }
    }
}