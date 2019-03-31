package br.com.felipebertanha.zupfilmes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.felipebertanha.zupfilmes.app.App
import br.com.felipebertanha.zupfilmes.data.model.Filme
import br.com.felipebertanha.zupfilmes.data.repository.FilmeRepository
import retrofit2.Callback


class FilmesViewModel : ViewModel() {
    private val repository by lazy { FilmeRepository(App.instance) }
    private var filmesFavoritos: LiveData<List<Filme>>? = null


    fun todosFilmesFavoritos(): LiveData<List<Filme>>? {
        if (filmesFavoritos == null) {

            filmesFavoritos = repository.todosFilmesFavoritos()
        }

        return filmesFavoritos
    }

    fun adicionarFilmeAosFavoritos(filme: Filme) {
        repository.adicionarFilmeAosFavoritos(filme)
    }

    fun removerFilmeDosFavoritos(filme: Filme) {
        repository.removerFilmeDosFavoritos(filme)
    }

    fun buscarFilmePorTituloWS(
        titulo: String,
        callback: Callback<Filme>
    ) {
        repository.buscarFilmePorTituloWS(titulo, callback)
    }
}