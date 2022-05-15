package com.example.clip_app

import Data.DataBase
import android.app.Fragment
import android.content.Intent
import com.example.clip_app.Adapters.Adapter_rd
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clip_app.Adapters.OnclickRecordatorio
import com.example.clip_app.items.ItemRd
import kotlinx.android.synthetic.main.fragment_rd.*

class RdFragment : Fragment(),OnclickRecordatorio {
    var context=this
    lateinit var intent: Intent
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rd, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CargarItem()
    }
   private fun CargarItem(){
        var db= DataBase(requireContext())
        val list = ArrayList<ItemRd>()
        var data = db.readRecuerdo()
        if (data != null) {
            for (i in 0 until data.size) {
                when (data.get(i).categoria){
                    "Recordatorio"->{var item=ItemRd(data.get(i).id.toString(),R.mipmap.reloj_foreground,data.get(i).categoria,data.get(i).name,data.get(i).fecha,data.get(i).hora)
                        list+=item}
                    "Examen"->{var item=ItemRd(data.get(i).id.toString(),R.mipmap.examen_foreground,data.get(i).categoria,data.get(i).name,data.get(i).fecha,data.get(i).hora)
                        list+=item}
                    "Tarea"->{var item=ItemRd(data.get(i).id.toString(),R.mipmap.tarea_foreground,data.get(i).categoria,data.get(i).name,data.get(i).fecha,data.get(i).hora)
                        list+=item}
                    "Reunion"->{var item=ItemRd(data.get(i).id.toString(),R.mipmap.reunion_foreground,data.get(i).categoria,data.get(i).name,data.get(i).fecha,data.get(i).hora)
                        list+=item}
                }
            }
            recycler_view_rd.layoutManager = LinearLayoutManager(activity)
            recycler_view_rd.setHasFixedSize(true)
            recycler_view_rd.adapter = Adapter_rd(list,context)
        }
    }
    override fun onSuperiorClick(item: ItemRd, position: Int) {
       intent = Intent(activity,EditActivity::class.java)
       intent.putExtra("textid",item.textId)
        startActivity(intent)
    }
}