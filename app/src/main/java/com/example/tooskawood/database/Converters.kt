package com.example.tooskawood.database

import androidx.room.TypeConverter
import com.example.tooskawood.Material


class Converters {
    @TypeConverter
    fun stringFromItemList(items: List<Material>): String {
        var str = ""
        for (item in items) {
            str += "${item.id}" + "," + item.name + "," + item.value + "-"
        }
        return str
    }

    @TypeConverter
    fun stringToItemList(itemsString: String): List<Material> {
        var list = arrayListOf<Material>()
        var strs = itemsString.split('-')
        for (str in strs) {
            if(str.isNullOrBlank())
                break
            var items = str.split(',')
            list.add(Material(items[0].toInt(), items[1],items[2].toInt()))
        }
        return list
    }
}