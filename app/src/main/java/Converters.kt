import androidx.room.TypeConverter
import com.example.tooskawood.Item

class Converters {
    @TypeConverter
    fun stringFromItemList(items: List<Item>): String {
        var str = ""
        for (item in items) {
            str += "${item.id}" + "," + item.name + "," + item.value + "-"
        }
        return str
    }

    @TypeConverter
    fun stringToItemList(itemsString: String): List<Item> {
        var list = arrayListOf<Item>()
        var strs = itemsString.split('-')
        for (str in strs) {
            if(str.isNullOrBlank())
                break
            var items = str.split(',')
            list.add(Item(items[0].toInt(), items[1],items[2]))
        }
        return list
    }
}