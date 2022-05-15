package com.example.clip_app.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clip_app.R
import com.example.clip_app.items.ItemRd
import kotlinx.android.synthetic.main.card_view.view.*

class Adapter_rd(
    var items:ArrayList<ItemRd>,var clickListner:OnclickRecordatorio

): RecyclerView.Adapter<RdViewHolder>(){
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RdViewHolder {
        var recuerdoViewHolder=RdViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false))
        return recuerdoViewHolder
    }
    override fun onBindViewHolder(holder: RdViewHolder, position: Int) {
        val currentItem = items[position]
        // holder.imageView.setImageResource(currentItem.imageResource)
        //holder.textView1.text = currentItem.text1
        //holder.textView2.text = currentItem.text2
        holder.initialize(items[position],clickListner)
    }
}
class RdViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var id=itemView.tv_idRd
    var imageView=itemView.imageView
    var titulo=itemView.tv_titulo
    var subtitulo=itemView.tv_subtitulo
    var fecha=itemView.tv_fecha
    var hora=itemView.tv_hora
    fun initialize(item:ItemRd,action:OnclickRecordatorio){
        id.text= item.textId.toString()
        imageView.setImageResource(item.imageResource)
        titulo.text=item.titulo
        subtitulo.text=item.subtitulo
        fecha.text=item.fecha
        hora.text=item.hora
        itemView.setOnClickListener(){
            action.onSuperiorClick(item,adapterPosition)
        }

    }
}
interface OnclickRecordatorio{
    fun onSuperiorClick(item:ItemRd,position: Int)
}