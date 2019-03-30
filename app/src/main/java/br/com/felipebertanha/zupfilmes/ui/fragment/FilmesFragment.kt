package br.com.felipebertanha.zupfilmes.ui.fragment;

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.felipebertanha.zupfilmes.R
import br.com.felipebertanha.zupfilmes.data.Filme
import br.com.felipebertanha.zupfilmes.eventbus.BuscarFilmeEvent
import br.com.felipebertanha.zupfilmes.retrofit.RetrofitInicializador
import br.com.felipebertanha.zupfilmes.ui.adapter.FilmeAdapter
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_filmes, container, false)

        rootView.filmes_lista_recyclerview.adapter = filmeAdapter

        return rootView
    }

    override fun onResume() {
        super.onResume()
        Log.e("onResume", "Aqui")
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        EventBus.getDefault().unregister(this)
        super.onPause()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBuscarFilme(event: BuscarFilmeEvent) {
        val query = event.query
        Log.e("onResume", query)

        RetrofitInicializador().filmeService().filme("b24090d0", query).enqueue(object : Callback<Filme> {

            override fun onResponse(call: Call<Filme>, response: Response<Filme>) {
                Log.e("Resp", response.body().toString())

                val list = listOf(response.body())
                filmeAdapter.setNewData(list)

            }

            override fun onFailure(call: Call<Filme>, t: Throwable) {
                Log.e("Fail", t.message)


            }
        })


        Toast.makeText(activity, query, Toast.LENGTH_LONG).show()
    }
}

