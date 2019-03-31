package br.com.felipebertanha.zupfilmes.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.felipebertanha.zupfilmes.R
import br.com.felipebertanha.zupfilmes.data.model.Filme
import br.com.felipebertanha.zupfilmes.glide.GlideApp
import br.com.felipebertanha.zupfilmes.ui.UiConstantes
import br.com.felipebertanha.zupfilmes.ui.viewmodel.FilmesViewModel
import kotlinx.android.synthetic.main.activity_detalhes.*

class DetalhesActivity : AppCompatActivity() {

    private lateinit var filme: Filme

    val viewModel: FilmesViewModel by lazy {
        ViewModelProviders.of(this).get(FilmesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)
        configurarToolbar()

        verificarFilmeRecebido()

        configurarFavoritarButton()
    }

    private fun configurarFavoritarButton() {
        detalhes_favoritar.setOnClickListener {
            if (filme.isFavorito) {
                filme.isFavorito = false
                viewModel.removerFilmeDosFavoritos(filme)
            } else {
                filme.isFavorito = true
                viewModel.adicionarFilmeAosFavoritos(filme)
            }

            exibirFilmeNoForm(filme)

        }
    }

    private fun configurarToolbar() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun verificarFilmeRecebido() {
        intent.getSerializableExtra(UiConstantes.KEY_FILME)?.let {
            filme = it as Filme

            //filme = viewModel.buscarFilmePorImdbId(filme.imdbID)
            exibirFilmeNoForm(filme)
        }
    }

    private fun exibirFilmeNoForm(filme: Filme) {
        detalhes_titulo.text = filme.titulo
        detalhes_ano.text = filme.anoLancamento
        detalhes_nota.text = filme.imdbNota
        detalhes_diretor.text = filme.diretor
        detalhes_resumo.text = filme.resumo
        detalhes_favoritar.text = if (filme.isFavorito) "Remover" else "Favoritar"

        GlideApp.with(this)
            .load(filme.poster)
            .into(detalhes_imagem)
    }

}
