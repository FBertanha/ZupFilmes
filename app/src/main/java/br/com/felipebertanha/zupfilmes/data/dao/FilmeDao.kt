package br.com.felipebertanha.zupfilmes.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.felipebertanha.zupfilmes.data.model.Filme

@Dao
interface FilmeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserir(filme: Filme)

    @Delete
    fun remover(filme: Filme)

    @Query("SELECT * FROM Filme")
    fun todos(): LiveData<List<Filme>>

    @Query("SELECT * FROM Filme WHERE imdbID = :imdbID")
    fun getPorImdbID(imdbID: String): Filme
}