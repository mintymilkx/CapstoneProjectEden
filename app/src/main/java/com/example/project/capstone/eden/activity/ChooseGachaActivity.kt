package com.example.project.capstone.eden.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.project.capstone.eden.R
import com.example.project.capstone.eden.databinding.ActivityChooseGachaBinding

class ChooseGachaActivity : AppCompatActivity() {

    private lateinit var btnCustomPopUp: Button
    private lateinit var binding: ActivityChooseGachaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseGachaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showGachaResult()

    }

    fun showGachaResult(){
        btnCustomPopUp = binding.btnExchange
        btnCustomPopUp.setOnClickListener {
            val dialog = Dialog(this)
            with(dialog) {
                setCancelable(false)
                setContentView(R.layout.popup_gacha)
            }

            val btnClose = dialog.findViewById<Button>(R.id.btn_close)
            btnClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

    }

}