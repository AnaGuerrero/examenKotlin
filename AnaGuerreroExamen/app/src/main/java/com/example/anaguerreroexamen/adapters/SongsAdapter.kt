package com.example.anaguerreroexamen.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.anaguerreroexamen.R
import com.example.anaguerreroexamen.modelos.Song
import kotlinx.android.synthetic.main.song_detail.view.*

class SongsAdapter(_context: Context, _arraySongs: ArrayList<Song>) :
    RecyclerView.Adapter<SongsAdapter.ViewHolder>() {

    var songsList = _arraySongs
    var context = _context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.song_detail, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvTitulo?.text = songsList.get(position).title
        holder?.tvArtist?.text = songsList.get(position).artist
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvTitulo = itemView.tvTitulo
        val tvArtist = itemView.tvArtista
    }
}