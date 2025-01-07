package com.example.lista_compra_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryAdapter(private val groceryList: List<GroceryItem>) :
    RecyclerView.Adapter<GroceryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cbSelected: CheckBox = view.findViewById(R.id.cbSelected)
        val tvQuantity: TextView = view.findViewById(R.id.tvQuantity)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvSection: TextView = view.findViewById(R.id.tvSection)
        val tvUrgent: TextView = view.findViewById(R.id.tvUrgent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grocery, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = groceryList[position]
        holder.cbSelected.isChecked = item.isSelected
        holder.tvQuantity.text = item.quantity.toString()
        holder.tvName.text = item.name
        holder.tvSection.text = item.section
        holder.tvUrgent.visibility = if (item.isUrgent) View.VISIBLE else View.GONE

        holder.cbSelected.setOnCheckedChangeListener { _, isChecked ->
            item.isSelected = isChecked
        }
    }

    override fun getItemCount() = groceryList.size
}
