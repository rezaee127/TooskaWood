package com.example.tooskawood

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Formula(@PrimaryKey(autoGenerate=true)var id:Int, var code:String, var materials:List<Material> )


data class Material(var id:Int, var name:String, var value:Long)