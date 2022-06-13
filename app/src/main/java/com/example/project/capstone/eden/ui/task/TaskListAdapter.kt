package com.example.project.capstone.eden.ui.task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.capstone.eden.R

class TaskListAdapter(private val context: Context, private val taskList: List<TaskModel>): RecyclerView.Adapter<TaskListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task_list_detail, parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task : TaskModel = taskList[position]

        holder.taskGoal.text = task.activity
        holder.point.text = task.exp.toString()
    }

    override fun getItemCount(): Int = taskList.size


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val taskGoal : TextView = itemView.findViewById(R.id.tv_task_in_myPlantDetail)
        val point : TextView = itemView.findViewById(R.id.tv_point_in_task_list)
    }
}