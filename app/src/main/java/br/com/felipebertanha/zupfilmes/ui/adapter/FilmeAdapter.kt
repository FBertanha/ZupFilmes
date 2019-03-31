package br.com.felipebertanha.zupfilmes.ui.adapter

import br.com.felipebertanha.zupfilmes.R
import br.com.felipebertanha.zupfilmes.data.model.Filme
import br.com.felipebertanha.zupfilmes.glide.GlideApp
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class FilmeAdapter() : BaseQuickAdapter<Filme, BaseViewHolder>(R.layout.filme_item) {
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

}