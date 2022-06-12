package com.example.project.capstone.eden.ui.myplant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.capstone.eden.R

class PlantAdapter(
    private val context: Context,
    private val plantList: ArrayList<PlantsModel>): RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PlantAdapter.PlantViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_my_plants, parent, false)
        return PlantAdapter.PlantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlantAdapter.PlantViewHolder, position: Int) {
        val myPlants: PlantsModel = plantList[position]

        Glide.with(context)
            .load(myPlants.imagePlant)
            .centerCrop()
            .into(holder.imagePlant)
        holder.plantName.text = myPlants.name
        holder.plantedDate.text = myPlants.plantedDate
        holder.plantType.text = myPlants.type
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    class PlantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imagePlant: ImageView = itemView.findViewById(R.id.iv_plants_in_myPlants)
        val plantName: TextView = itemView.findViewById(R.id.tv_plant_name_in_myPlants)
        val plantedDate: TextView = itemView.findViewById(R.id.tv_planted_date_in_myPlants)
        val plantType: TextView = itemView.findViewById(R.id.tv_plant_type_in_myPlants)
    }

}