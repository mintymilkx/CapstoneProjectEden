package com.example.project.capstone.eden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.project.capstone.eden.databinding.ActivityChooseGachaBinding

class ChooseGachaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseGachaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseGachaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showGachaResult()

    }

    fun showGachaResult(){
        binding.btnExchange.setOnClickListener {
            val view = View.inflate(this, R.layout.popup_gacha, null)

            val builder = AlertDialog.Builder(this)
            builder.setView(view)

            val dialog = builder.create()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }


    }
}