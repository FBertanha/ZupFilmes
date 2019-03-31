package br.com.felipebertanha.zupfilmes.ui.fragment;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.felipebertanha.zupfilmes.R
import br.com.felipebertanha.zupfilmes.data.model.Filme
import br.com.felipebertanha.zupfilmes.eventbus.BuscarFilmeEvent
import br.com.felipebertanha.zupfilmes.eventbus.ExibirDetalhesFilmeEvent
import br.com.felipebertanha.zupfilmes.ui.adapter.FilmeAdapter
import br.com.felipebertanha.zupfilmes.ui.viewmodel.FilmesViewModel
import kotlinx.android.synthetic.main.fragment_filmes.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmesFragment : Fragment() {

    private val filmeAdapter by lazy {
        FilmeAdapter()
    }

    val viewModel: FilmesViewModel by lazy {
        ViewModelProviders.of(this).get(FilmesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_filmes, container, false)

        configurarListaFilmesRecyclerView(rootView)

        return rootView
    }

    private fun configurarListaFilmesRecyclerView(rootView: View) {
        rootView.filmes_lista.adapter = filmeAdapter
        filmeAdapter.setOnItemClickListener { adapter, view, position ->
            val filme = adapter.getItem(position) as Filme

            EventBus.getDefault().post(ExibirDetalhesFilmeEvent(filme))
        }
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        EventBus.getDefault().unregister(this)
        super.onPause()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBuscarFilme(event: BuscarFilmeEvent) {
        val query = event.query
        viewModel.buscarFilmePorTituloWS(query, object : Callback<Filme> {

            override fun onResponse(call: Call<Filme>, response: Response<Filme>?) {
                response?.let {
                    val list = listOf(it.body())
                    filmeAdapter.setNewData(list)
                }
            }

            override fun onFailure(call: Call<Filme>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}

