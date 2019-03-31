package br.com.felipebertanha.zupfilmes.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import br.com.felipebertanha.zupfilmes.data.dao.FilmeDao
import br.com.felipebertanha.zupfilmes.data.model.Filme
import br.com.felipebertanha.zupfilmes.data.sqlite.AppDatabase
import br.com.felipebertanha.zupfilmes.retrofit.RetrofitConstantes
import br.com.felipebertanha.zupfilmes.retrofit.RetrofitInicializador
import br.com.felipebertanha.zupfilmes.retrofit.services.FilmeService
import retrofit2.Callback

class FilmeRepository(val context: Context) {
    private val filmeDao: FilmeDao by lazy {
        AppDatabase.getAppDatabase(context)!!.filmeModel()
    }

    private val filmeService: FilmeService by lazy {
        RetrofitInicializador().filmeService()
    }


    fun todosFilmesFavoritos(): LiveData<List<Filme>> {
        return filmeDao.todos()
    }

    fun adicionarFilmeAosFavoritos(filme: Filme) {
        filmeDao.inserir(filme)
    }

    fun removerFilmeDosFavoritos(filme: Filme) {
        filmeDao.remover(filme)
    }

    fun buscarFilmePorTituloWS(
        titulo: String,
        callback: Callback<Filme>
    ) {
        filmeService.getFilmePorNome(RetrofitConstantes.API_KEY, titulo)
            .enqueue(callback)
    }

    fun buscarFilmePorImdbId(imdbID: String): Filme {
        return filmeDao.getPorImdbID(imdbID)
    }

}
