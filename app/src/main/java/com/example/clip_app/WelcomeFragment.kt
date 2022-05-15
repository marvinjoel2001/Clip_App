package com.example.clip_app

import Data.DataBase
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_welcome.*
import java.util.Observer


class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db=DataBase(requireContext())
        if (db.comprobarUser()){
            view.findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment2)
        }else{
            tv_user.setText(db.readUser())
        }
        btn_comenzar.setOnClickListener(){
            var intentAll= Intent(activity,BottomNavigationBar::class.java)
            startActivity(intentAll)
        }
    }
}