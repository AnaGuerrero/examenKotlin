package com.example.anaguerreroexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvRegistro.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id==tvRegistro.id){
            val intent = Intent(this, RegistroDatos::class.java).apply { }
            startActivity(intent)
        }
    }
}
