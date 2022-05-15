package com.example.clip_app

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
        val shareFragment=NewRecorder_Fragment()



        makeCurrentFragment(homeFragment)


        //sirve para hacer clic y navegar entre los fragments
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> makeCurrentFragment(homeFragment)
                R.id.nav_share ->makeCurrentFragment(shareFragment)
            }
            true
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