package com.example.clip_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import com.example.clip_app.Interface.iComunicaFragments
import kotlinx.android.synthetic.main.activity_edit.*


class EditActivity : AppCompatActivity(){
    lateinit var bundle:Bundle
    lateinit var idText:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        bundle=Bundle()
        idText=intent.getStringExtra("textid")




        bundle.putString("MIID",idText)
        findNavController(R.id.fragmentEdit).setGraph(R.navigation.nav_graph_edit,bundle)





    }
}
