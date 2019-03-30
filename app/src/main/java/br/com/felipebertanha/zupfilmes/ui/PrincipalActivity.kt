package br.com.felipebertanha.zupfilmes.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import br.com.felipebertanha.zupfilmes.R
import br.com.felipebertanha.zupfilmes.eventbus.BuscarFilmeEvent
import br.com.felipebertanha.zupfilmes.ui.fragment.MyPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_principal.*
import org.greenrobot.eventbus.EventBus

class PrincipalActivity : AppCompatActivity() {

    private var mMyPagerAdapter: MyPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        configurarToolbar()
        configurarTabs()

    }

    private fun configurarToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun configurarTabs() {
        mMyPagerAdapter = MyPagerAdapter(supportFragmentManager)
        container.adapter = mMyPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        configurarPesquisa(menu)
        return true
    }

    private fun configurarPesquisa(menu: Menu) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_filmes_pesquisar).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = "Pesquise por títulos"


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                EventBus.getDefault().post(BuscarFilmeEvent(query))
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })
    }

}
