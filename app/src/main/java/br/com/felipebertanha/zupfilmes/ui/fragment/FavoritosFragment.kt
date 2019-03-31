package br.com.felipebertanha.zupfilmes.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.felipebertanha.zupfilmes.R
import br.com.felipebertanha.zupfilmes.data.model.Filme
import br.com.felipebertanha.zupfilmes.eventbus.ExibirDetalhesFilmeEvent
import br.com.felipebertanha.zupfilmes.ui.adapter.FilmeAdapter
import br.com.felipebertanha.zupfilmes.ui.viewmodel.FilmesViewModel
import kotlinx.android.synthetic.main.fragment_favoritos.view.*
import org.greenrobot.eventbus.EventBus

class FavoritosFragment : Fragment() {

    private val filmeAdapter by lazy {
        FilmeAdapter()
    }

    val viewModel: FilmesViewModel by lazy {
        ViewModelProviders.of(this).get(FilmesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_favoritos, container, false)

        configurarListaFilmesRecyclerView(rootView)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.todosFilmesFavoritos()
            ?.observe(this, Observer {
                filmeAdapter.setNewData(it)
            })
    }


    private fun configurarListaFilmesRecyclerView(rootView: View) {
        rootView.favoritos_lista.adapter = filmeAdapter
        filmeAdapter.setOnItemClickListener { adapter, view, position ->
            val filme = adapter.getItem(position) as Filme

            EventBus.getDefault().post(ExibirDetalhesFilmeEvent(filme))
        }
    }


}
