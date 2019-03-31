package br.com.felipebertanha.zupfilmes.data.sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.felipebertanha.zupfilmes.data.dao.FilmeDao
import br.com.felipebertanha.zupfilmes.data.model.Filme

@Database(entities = [Filme::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val DATABASE_NAME = "zup_filmes.db"

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }

    }

    abstract fun filmeModel(): FilmeDao
}