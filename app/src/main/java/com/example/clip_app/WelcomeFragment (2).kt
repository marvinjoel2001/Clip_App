package com.example.clip_app

import Data.DataBase
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_welcome.*


class WelcomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (comprobarnombre()){
            view?.findNavController()?.navigate(R.id.action_welcomeFragment_to_loginFragment2)
        }
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (comprobarnombre()){
            view.findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment2)
        }
        btn_comenzar.setOnClickListener(){
                var intent=Intent(activity,BottomNavigationBar::class.java)
                startActivity(intent)
        }
    }
    fun comprobarnombre():Boolean{
        var db=DataBase(requireContext())
        var comprobador=true
        var data=db.readUser()
        if (data!=null){
            comprobador=false
            Toast.makeText(activity, "si llega ", Toast.LENGTH_SHORT).show()
        }
        return comprobador
    }
}