package br.com.felipebertanha.zupfilmes.ui.adapter

import android.widget.Filter
import android.widget.Filterable
import br.com.felipebertanha.zupfilmes.R
import br.com.felipebertanha.zupfilmes.data.model.Filme
import br.com.felipebertanha.zupfilmes.glide.GlideApp
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import java.util.*

class FilmeAdapter() : BaseQuickAdapter<Filme, BaseViewHolder>(R.layout.filme_item), Filterable {

    internal var filter: Filter = RecyclerViewFilter()
    internal var mFilterData: MutableList<Filme>? = null
    override fun convert(helper: BaseViewHolder, item: Filme) {

        if (item.imdbID == null) {
            return
        }
        helper.setText(R.id.filme_item_titulo, item.titulo)
            .setText(R.id.filme_item_ano, item.anoLancamento)
            .setText(R.id.filme_item_nota, item.imdbNota + "/10")
            .setText(R.id.filme_item_diretor, item.diretor)

        GlideApp.with(mContext)
            .load(item.poster)
            .placeholder(R.drawable.ic_broken_image_accent_24dp)
            .into(helper.getView(R.id.filme_item_imagem))
    }

    override fun setNewData(data: MutableList<Filme>?) {
        super.setNewData(data)
        mFilterData = data
    }


    override fun getFilter(): Filter? {
        if (filter == null) {
            filter = RecyclerViewFilter()
        }
        return filter
    }

    private inner class RecyclerViewFilter : Filter() {

        override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
            val results = Filter.FilterResults()
            var newValues: MutableList<Filme>? = ArrayList()


            val prefixString = charSequence.toString()
            if (prefixString.isEmpty()) {
                newValues = mFilterData
            } else {
                for (cliente in mFilterData!!) {
                    if (cliente.titulo.toLowerCase().contains(prefixString.toLowerCase())

                    ) {
                        newValues!!.add(cliente)
                    }
                }
            }
            results.values = newValues
            results.count = newValues!!.size
            return results
        }

        override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
            mData = filterResults.values as List<Filme>
            //if (filterResults.count > 0) {
            //    notifyDataSetChanged();
            //} else {
            //mData = mFilterData;
            notifyDataSetChanged()
            //}
        }
    }

}