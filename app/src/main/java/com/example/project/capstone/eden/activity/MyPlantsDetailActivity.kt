package com.example.project.capstone.eden.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project.capstone.eden.R
import com.example.project.capstone.eden.databinding.ActivityMyPlantsDetailBinding

class MyPlantsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPlantsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyPlantsDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}