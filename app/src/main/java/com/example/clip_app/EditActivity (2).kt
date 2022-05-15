package com.example.clip_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        var idRd=intent.getStringExtra("ID")
        //var idBundle=Bundle()
        //idBundle.putInt("BunId",idRd)

    }
}