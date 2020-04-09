package com.example.anaguerreroexamen

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.anaguerreroexamen.modelos.Perfil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_registro_datos.*

class RegistroDatos : AppCompatActivity(), View.OnClickListener {

    private val PERMISSION_CODE = 1000;
    private val IMAGE_CAPTURE_CODE = 1001
    var image_uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_datos)

        btnTomarFoto.setOnClickListener(this)
        btnRegistrarse.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id==btnTomarFoto.id){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                }
                else{
                    tomarFoto()
                }
            }
            else{
                tomarFoto()
            }
        }else if(v.id==btnRegistrarse.id){
            if(!etUserName.text.toString().equals("")){
                if(!etName.text.toString().equals("")){
                    if(!etBiography.text.toString().equals("")){
                        if(!Uri.EMPTY.equals(image_uri)){
                            val perfil = Perfil(image_uri?.path, etUserName.text.toString(), etName.text.toString(), etBiography.text.toString())
                            configureSharedPreferences(perfil)
                        }else{
                            Toast.makeText(applicationContext, "Por favor, toma una foto!", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(applicationContext, "Por favor, ingresa una pequeña biografía", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext, "Por favor, ingresa tu nombre completo", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext, "Por favor, ingresa un nombre de usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun tomarFoto(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    tomarFoto()
                }
                else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            ivVerFoto.setImageURI(image_uri)
        }
    }

    fun configureSharedPreferences(perfil: Perfil){
        val gson = Gson()
        val perfilText:String = gson.toJson(perfil)
        val EXTRA_PERFIL: String = "extraPerfil"

        var PRIVATE_MODE = 0
        val PREF_NAME = "perfil"
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)

        val editor = sharedPref.edit()
        editor.putString(getString(R.string.perfil), perfilText)
        editor.apply()

        val intent = Intent(this, Choose::class.java).apply { }
        intent.putExtra(EXTRA_PERFIL, perfil)
        startActivity(intent)
    }
}
