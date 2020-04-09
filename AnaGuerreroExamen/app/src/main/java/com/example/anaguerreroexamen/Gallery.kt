package com.example.anaguerreroexamen

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.example.anaguerreroexamen.adapters.GridViewAdapter
import com.example.anaguerreroexamen.modelos.DrawableItem
import com.example.anaguerreroexamen.modelos.Perfil
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.android.synthetic.main.dialog_img.view.*
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import java.util.ArrayList

class Gallery : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        var drawableList:ArrayList<DrawableItem> = createDrawableList()

        var gridAdapter = GridViewAdapter(this, drawableList)
        gvGallery.adapter = gridAdapter

        gvGallery.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                createDialog(drawableList.get(position))
            }
        }
    }

    fun createDrawableList(): ArrayList<DrawableItem>{
        var listDrawable: ArrayList<DrawableItem> = arrayListOf()

        listDrawable.add(DrawableItem(resources.getDrawable(R.drawable.imagen1)))
        listDrawable.add(DrawableItem(resources.getDrawable(R.drawable.imagen2)))
        listDrawable.add(DrawableItem(resources.getDrawable(R.drawable.imagen3)))
        listDrawable.add(DrawableItem(resources.getDrawable(R.drawable.imagen4)))
        listDrawable.add(DrawableItem(resources.getDrawable(R.drawable.imagen5)))
        listDrawable.add(DrawableItem(resources.getDrawable(R.drawable.imagen6)))
        listDrawable.add(DrawableItem(resources.getDrawable(R.drawable.imagen7)))
        listDrawable.add(DrawableItem(resources.getDrawable(R.drawable.imagen8)))
        listDrawable.add(DrawableItem(resources.getDrawable(R.drawable.imagen9)))
        listDrawable.add(DrawableItem(resources.getDrawable(R.drawable.imagen10)))

        return listDrawable
    }

    fun createDialog(itemDrawable:DrawableItem){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_img, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        mDialogView.ivFoto.setImageDrawable(itemDrawable.imagen)
        val  mAlertDialog = mBuilder.show()
        mDialogView.btnCerrar.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }
}
