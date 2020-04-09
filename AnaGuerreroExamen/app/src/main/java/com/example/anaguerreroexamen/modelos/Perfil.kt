package com.example.anaguerreroexamen.modelos

import java.io.Serializable

class Perfil: Serializable{
    var imageUri: String? = ""
    var userName: String = ""
    var name: String = ""
    var biography: String = ""

    constructor(_imageUri:String?, _userName:String, _name:String, _biography:String){
        imageUri=_imageUri
        userName=_userName
        name=_name
        biography=_biography
    }
}