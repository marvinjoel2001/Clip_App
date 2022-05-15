package com.example.clip_app

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.android.synthetic.main.activity_bottom_navigation_bar.*

class BottomNavigationBar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_bar)
        val homeFragment=RdFragment()
        val shareFragment=RdShareFragment()
        val home_select=R.drawable.ic_reloj_select
        val share_select=R.drawable.ic_compartir_select
        val home_normal=R.drawable.ic_reloj
        val share_normal=R.drawable.ic_compartir
        val color_secondary=R.color.accent
        val color_negro=R.color.negro
        //empieza el primer fragment
        makeCurrentFragment(homeFragment)
        btn_añadir_floating.setOnClickListener(){
            var intentañadir=Intent(this,AnadirActivity::class.java)
            startActivity(intentañadir)
        }
        rd_home_fragment.setOnClickListener(){
            makeCurrentFragment(homeFragment)
            imv_home.setImageResource(home_select)
            imv_share.setImageResource(share_normal)
            tv_home.setTextColor(tv_home.context.resources.getColor(color_secondary))
            tv_share.setTextColor(tv_home.context.resources.getColor(color_negro))
        }
        rd_share_fragment.setOnClickListener(){
            makeCurrentFragment(shareFragment)
            imv_home.setImageResource(home_normal)
            imv_share.setImageResource(share_select)
            tv_share.setTextColor(tv_home.context.resources.getColor(color_secondary))
            tv_home.setTextColor(tv_home.context.resources.getColor(color_negro))
        }
    }
    //metodo que reemplaza los fragmen en el container
    private fun makeCurrentFragment(fragment:Fragment){
      supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragment_container,fragment)
          commit()
      }
   }


}