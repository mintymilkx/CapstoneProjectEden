package com.example.project.capstone.eden.ui.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.capstone.eden.databinding.ActivityTaskListDetailBinding
import com.google.firebase.firestore.FirebaseFirestore

class TaskListDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskListDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasklist.apply {
            layoutManager = LinearLayoutManager(this@TaskListDetailActivity)

            fetchData()
        }
    }

    private fun fetchData(){
        FirebaseFirestore.getInstance().collection("tasks")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    val task = documents.toObjects(TaskModel::class.java)
                    binding.rvTasklist.adapter = TaskListAdapter(this, task)
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "An error occured ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }


}