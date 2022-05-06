package com.example.tooskawood.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tooskawood.Formula
import com.example.tooskawood.R

class FormulaAdapter(var onClickItem:(Int)->Unit) : ListAdapter<Formula, FormulaAdapter.ViewHolder>(WordDiffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewFormulaId=view.findViewById<TextView>(R.id.textViewFormulaId)
        val textViewFormulaCode=view.findViewById<TextView>(R.id.textViewFormulaCode)
        val formulaRowItem=view.findViewById<View>(R.id.linearLayout1)

        fun bind(formula:Formula,onClickItem: (Int) -> Unit){
            textViewFormulaId.text=formula.id.toString()
            textViewFormulaCode.text=formula.code
            formulaRowItem.setOnClickListener {
                onClickItem(formula.id)
            }
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.formula_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bind( getItem(position),onClickItem)

    }


    object WordDiffCallback : DiffUtil.ItemCallback<Formula>() {
        override fun areItemsTheSame(oldItem: Formula, newItem: Formula): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Formula, newItem: Formula): Boolean {
            return oldItem.id == newItem.id
        }
    }

}
