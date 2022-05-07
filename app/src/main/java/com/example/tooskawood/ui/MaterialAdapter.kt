package com.example.tooskawood.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tooskawood.Material
import com.example.tooskawood.R
import java.lang.String.format


class MaterialAdapter(var dataSet:List<Material>,var sumValue:Long,var numberForConvert:Long)
    : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewMaterialId=view.findViewById<TextView>(R.id.textViewMaterialId)
        var textViewMaterialName=view.findViewById<TextView>(R.id.textViewMaterialName)
        var textViewMaterialValue=view.findViewById<TextView>(R.id.textViewMaterialValue)
        var textViewConverter=view.findViewById<TextView>(R.id.textViewConverter)
        var materialRowItem=view.findViewById<View>(R.id.MaterialRowItem)

        @SuppressLint("DefaultLocale")
        fun bind(material:Material,sumValue: Long, numberForConvert:Long){
            textViewMaterialId.text=material.id.toString()
            textViewMaterialName.text=material.name
            textViewMaterialValue.text=material.value.toString()
            if( numberForConvert==0L){
                textViewConverter.text="0"
            }else{
                val x = (material.value * numberForConvert).toDouble()/sumValue.toDouble()
                textViewConverter.text= format("%.3f", x)
            }
        }

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.material_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bind (dataSet[position],sumValue, numberForConvert)
    }


    override fun getItemCount() = dataSet.size

}
