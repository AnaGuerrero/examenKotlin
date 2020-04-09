package com.example.anaguerreroexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.anaguerreroexamen.modelos.Perfil
import kotlinx.android.synthetic.main.activity_choose.*

class Choose : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        val EXTRA_PERFIL: String = "extraPerfil"

        val perfil: Perfil? = intent.getSerializableExtra(EXTRA_PERFIL) as? Perfil

        cvGallery.setOnClickListener(this)
        cvMusic.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id==cvGallery.id){
            val intent = Intent(this, Gallery::class.java).apply { }
            startActivity(intent)
        }else if(v.id==cvMusic.id){
            val intent = Intent(this, Musica::class.java).apply { }
            startActivity(intent)
        }
    }
}
