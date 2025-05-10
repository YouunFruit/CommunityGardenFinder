package com.youunfruit.communitygardenfinder.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.youunfruit.communitygardenfinder.R
import com.youunfruit.communitygardenfinder.objects.Garden

class GardenAdapter(
    private val gardens: List<Garden>,
    private val onItemClick: (Garden) -> Unit
) : RecyclerView.Adapter<GardenAdapter.GardenViewHolder>() {

    inner class GardenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gardenName: TextView = itemView.findViewById(R.id.gardenName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_garden, parent, false)
        return GardenViewHolder(view)
    }

    override fun onBindViewHolder(holder: GardenViewHolder, position: Int) {
        val garden = gardens[position]
        holder.gardenName.text = garden.name
        holder.itemView.setOnClickListener { onItemClick(garden) }
    }

    override fun getItemCount(): Int = gardens.size
}
