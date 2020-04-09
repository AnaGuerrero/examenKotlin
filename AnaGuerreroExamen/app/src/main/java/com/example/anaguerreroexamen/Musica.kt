package com.example.anaguerreroexamen

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anaguerreroexamen.adapters.SongsAdapter
import com.example.anaguerreroexamen.modelos.Song
import kotlinx.android.synthetic.main.activity_musica.*

class Musica : AppCompatActivity(), View.OnClickListener{

    lateinit var mediaPlayer:MediaPlayer
    var songList:ArrayList<Song> = arrayListOf()
    var currentSong:Int = 0
    var pause:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musica)

        songList = getSongs()

        rvSongs.layoutManager = LinearLayoutManager(this)
        rvSongs.adapter = SongsAdapter(this, songList)
        rvSongs.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                startSong(position)
            }
        })

        ibPrevious.setOnClickListener(this)
        ibPlay.setOnClickListener(this)
        ibPause.setOnClickListener(this)
        ibNext.setOnClickListener(this)
        ibStop.setOnClickListener(this)
    }

    fun getSongs():ArrayList<Song>{
        var songArray:ArrayList<Song> = arrayListOf()

        songArray.add(Song(R.raw.for_your_entertainment, "For Your Entertainment", "Adam Lambert"))
        songArray.add(Song(R.raw.bad, "Bad", "David Guetta y Showtek"))
        songArray.add(Song(R.raw.cant_hold_us, "Cant Hold Us", "Macklemore"))
        songArray.add(Song(R.raw.como_te_voy_a_olvidar, "Como te voy a olvidar", "Los Àngeles Azules"))
        songArray.add(Song(R.raw.diecisiete_anios, "17 años", "Los Ángeles Azules"))
        songArray.add(Song(R.raw.entrega_de_amor, "Entrega de Amor", "Los Ángeles Azules"))
        songArray.add(Song(R.raw.firework, "Firework", "Katy Perry"))
        songArray.add(Song(R.raw.i_like_it, "I Like It", "Enrique Iglesias y Pitbull"))
        songArray.add(Song(R.raw.juventud, "Juventud", "Los Ángeles Azules"))
        songArray.add(Song(R.raw.my_milkshake, "My Milkshake", "Kelis"))

        return songArray
    }

    fun startSong(position: Int){
        currentSong=position
        var isNotNull:Boolean = this::mediaPlayer.isInitialized

        if(isNotNull){
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(applicationContext, songList.get(position).id)
            mediaPlayer.start()
        }else{
            mediaPlayer = MediaPlayer.create(applicationContext, songList.get(position).id)
            mediaPlayer.start()
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }

    override fun onClick(v: View?) {
        var isNotNull:Boolean = this::mediaPlayer.isInitialized

        if(isNotNull){
            if (v != null) {
                if(v.id==ibPrevious.id){
                    previousSong()
                }else if(v.id==ibPlay.id){
                    if(!mediaPlayer.isPlaying){
                        if(pause){
                            mediaPlayer.seekTo(mediaPlayer.currentPosition)
                            mediaPlayer.start()
                        }else{
                            playSong(songList.get(currentSong).id)
                        }
                    }
                }else if(v.id==ibPause.id){
                    if(mediaPlayer.isPlaying){
                        mediaPlayer.pause()
                        pause=true
                    }else{
                        pause=false
                    }
                }else if(v.id==ibStop.id){
                    mediaPlayer.stop()
                    pause = false
                }else if(v.id==ibNext.id){
                    nextSong()
                }
            }
        }
    }

    fun previousSong(){
        if(currentSong>0){
            currentSong--
            playSong(songList.get(currentSong).id)
        }else{
            currentSong=songList.size-1
            playSong(songList.get(currentSong).id)
        }
    }

    fun nextSong(){
        if(currentSong<songList.size-1){
            currentSong++
            playSong(songList.get(currentSong).id)
        }else{
            currentSong=0
            playSong(songList.get(currentSong).id)
        }
    }

    fun playSong(id:Int){
        mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(applicationContext, id)
        mediaPlayer.start()
    }
}
