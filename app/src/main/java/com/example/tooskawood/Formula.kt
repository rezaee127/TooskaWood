package com.example.tooskawood

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Formula(@PrimaryKey(autoGenerate=true)var id:Int, var code:String, var items:List<Item> )


data class Item(var id:Int,var name:String,var value:Int)