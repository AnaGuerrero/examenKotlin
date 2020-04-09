package com.example.anaguerreroexamen.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.anaguerreroexamen.R
import com.example.anaguerreroexamen.modelos.DrawableItem
import kotlinx.android.synthetic.main.img_detail.view.*

class GridViewAdapter: BaseAdapter {

    var drawableList:ArrayList<DrawableItem>
    var context:Context

    constructor(_context: Context, _drawableList:ArrayList<DrawableItem>){
        drawableList = _drawableList
        context = _context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val drawableItem = this.drawableList.get(position)

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var imageView = inflator.inflate(R.layout.img_detail, null)
        imageView.ivFoto.setImageDrawable(drawableItem.imagen)

        return imageView
    }

    override fun getItem(position: Int): Any {
        return drawableList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return drawableList.size
    }

}