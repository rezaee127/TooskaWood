package com.example.tooskawood.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tooskawood.Material
import com.example.tooskawood.R


class MaterialAdapter(var dataSet:List<Material>) : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewMaterialId=view.findViewById<TextView>(R.id.textViewMaterialId)
        var textViewMaterialName=view.findViewById<TextView>(R.id.textViewMaterialName)
        var textViewMaterialValue=view.findViewById<TextView>(R.id.textViewMaterialValue)

        fun bind(material:Material){
            textViewMaterialId.text=material.id.toString()
            textViewMaterialName.text=material.name
            textViewMaterialValue.text=material.value.toString()
        }

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.material_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bind (dataSet[position])
    }


    override fun getItemCount() = dataSet.size

}
