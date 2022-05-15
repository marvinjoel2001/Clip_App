package com.example.clip_app

import Data.DataBase
import Data.User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       btn_siguiente.setOnClickListener(){
            var nombre=et_nombre.text.toString()
            guardarnombre(nombre)
            if (nombre.length> 0){
                it.findNavController().navigate(R.id.action_loginFragment2_to_welcomeFragment)
            }else{
                Toast.makeText(context,"Nombre vacio",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun guardarnombre(name:String){
        var db=DataBase(requireContext())
        var usuario=User(1,name)
        db.saveUser(usuario)
    }
}