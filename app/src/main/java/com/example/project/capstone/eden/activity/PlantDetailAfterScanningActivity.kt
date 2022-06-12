package com.example.project.capstone.eden.activity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.project.capstone.eden.R
import com.example.project.capstone.eden.databinding.ActivityPlantDetailAfterScanningBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PlantDetailAfterScanningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlantDetailAfterScanningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //firestore
        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("plants").document()
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "Current data: ${snapshot.data}")
            } else {
                Log.d(TAG, "Current data: null")
            }
        }


        //ui
        binding = ActivityPlantDetailAfterScanningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCloseInAfterScan

        binding.btAddToMyPlants

        binding.tvPlantTypeInMyPlantDetailAfterScan
        binding.tvPlantNameInMyPlantDetailAfterScan
        binding.tvPhysicalAppearanceAfterScan
        binding.tvBenefitDescriptionAfterScan
        binding.tvXpInMyPlantDetailAfterScan




    }
}