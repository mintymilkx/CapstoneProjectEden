package com.example.project.capstone.eden.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.project.capstone.eden.R
import com.example.project.capstone.eden.databinding.FragmentGachaBinding

class GachaFragment : Fragment() {
    private lateinit var btnCustomPopUp: Button
    private lateinit var binding: FragmentGachaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGachaBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        showGachaResult()
        return binding.root


    }
    private fun showGachaResult() {
        btnCustomPopUp = binding.btnExchange
        btnCustomPopUp.setOnClickListener {
            val dialog = Dialog(requireActivity())
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