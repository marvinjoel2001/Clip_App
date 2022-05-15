package com.example.clip_app.Adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clip_app.items.ItemRd
import com.example.clip_app.items.ItemRdShare
import kotlinx.android.synthetic.main.card_view.view.*

class Adapter_rdsharevar(var itemshare:ArrayList<ItemRdShare>):RecyclerView.Adapter<RdShareViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RdShareViewHolder {
        TODO("Not yet implemented")
    }
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    override fun onBindViewHolder(holder: RdShareViewHolder, position: Int) {

    }
}
class RdShareViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var id=itemView.tv_idRd
    var imageView=itemView.imageView
    var titulo=itemView.tv_titulo
    var subtitulo=itemView.tv_subtitulo
    var fecha=itemView.tv_fecha
    var hora=itemView.tv_hora

    fun initialize(item: ItemRd){
        id.text= item.textId.toString()
        imageView.setImageResource(item.imageResource)
        titulo.text=item.titulo
        subtitulo.text=item.subtitulo
        fecha.text=item.fecha
        hora.text=item.hora
    }
}
interface OnclickShare{
    fun onSuperiorClick(item: ItemRd, position: Int)
}
