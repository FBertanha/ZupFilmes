package br.com.felipebertanha.zupfilmes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.felipebertanha.zupfilmes.R
import br.com.felipebertanha.zupfilmes.data.model.Filme
import br.com.felipebertanha.zupfilmes.glide.GlideApp
import br.com.felipebertanha.zupfilmes.ui.UiConstantes
import br.com.felipebertanha.zupfilmes.ui.viewmodel.DetalhesFimeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detalhes_filme.*

class DetalhesFilmeActivity : AppCompatActivity() {

    val viewModel: DetalhesFimeViewModel by lazy {
        ViewModelProviders.of(this).get(DetalhesFimeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_filme)
        configurarToolbar()
        configurarFab()
        verificarFilmeRecebido()
    }

    private fun verificarFilmeRecebido() {
        intent.getSerializableExtra(UiConstantes.KEY_FILME)?.let {
            val filme = it as Filme
            exibirFilmeNoForm(filme)
        }
    }

    private fun exibirFilmeNoForm(filme: Filme) {
        GlideApp.with(this)
            .load(filme.poster)
            .into(detalhes_filme_imagem)
    }

    private fun configurarFab() {
        fab.setOnClickListener { view ->


            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun configurarToolbar() {
        setSupportActionBar(toolbar)
    }
}
