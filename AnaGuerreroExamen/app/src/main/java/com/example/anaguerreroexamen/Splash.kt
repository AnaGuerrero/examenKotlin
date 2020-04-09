package com.example.anaguerreroexamen

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anaguerreroexamen.modelos.Perfil
import com.google.gson.Gson

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        verificaUsuario()
    }

    fun verificaUsuario(){
        var PRIVATE_MODE = 0
        val PREF_NAME = "perfil"
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)

        var jsonPerfil: String? = sharedPref.getString(getString(R.string.perfil), "")
        if(!jsonPerfil.equals("")){
            val EXTRA_PERFIL: String = "extraPerfil"
            var perfil:Perfil
            val gson = Gson()
            perfil = gson.fromJson(jsonPerfil, Perfil::class.java)

            val intent = Intent(this, Choose::class.java).apply { }
            intent.putExtra(EXTRA_PERFIL, perfil)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, RegistroDatos::class.java).apply { }
            startActivity(intent)
            finish()
        }
    }
}
