package com.example.project.capstone.eden.ui.scan

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.project.capstone.eden.R
import com.example.project.capstone.eden.activity.HomeActivity
import com.example.project.capstone.eden.databinding.ActivityPlantDetailAfterScanningBinding
import com.example.project.capstone.eden.ui.myplant.MyPlantsFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class PlantDetailAfterScanningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlantDetailAfterScanningBinding
    private lateinit var txt_names: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantDetailAfterScanningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //firestore
        val db = FirebaseFirestore.getInstance()
        val intent = intent
        val resultPlant = intent.getStringExtra("resultPlant")
        val model_name = intent.getStringExtra("model_name")
        val name = intent.getStringExtra("name")

        val plantName = binding.tvXpInMyPlantDetailAfterScan
        val type = binding.tvPlantTypeInMyPlantDetailAfterScan
        val pyshicalApp = binding.tvPhysicalAppearanceAfterScan
        val benefit = binding.tvBenefitDescriptionAfterScan
        val imgPlant = binding.ivPlant

        if (resultPlant != null) {
            txt_names = resultPlant

        } else{
            if (model_name != null) {
                txt_names = model_name
            }
        }

        val docRef = db.collection("plants").document("${txt_names}")

        docRef.get()
            .addOnSuccessListener{ document ->
                if(document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    plantName.text = document.getString("name")

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }

        val docRef2 = db.collection("plants").document("${txt_names}")

        docRef2.get()
            .addOnSuccessListener{ document ->
                if(document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    type.text = document.getString("type")

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }

        val docRef3 = db.collection("plants").document("${txt_names}")

        docRef3.get()
            .addOnSuccessListener{ document ->
                if(document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    pyshicalApp.text = document.getString("pyshicalApp")

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }

        val docRef4 = db.collection("plants").document("${txt_names}")

        docRef4.get()
            .addOnSuccessListener{ document ->
                if(document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    benefit.text = document.getString("benefit")

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }

        val docRef5 = db.collection("plants").document("${txt_names}")

        docRef5.get()
            .addOnSuccessListener{ document ->
                if(document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    var link = document.getString("imageUrl")
                    Picasso.get().load(link).into(imgPlant)

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }


        binding.btCloseInAfterScan.setOnClickListener{
            startActivity(Intent(this, CameraActivity::class.java))
        }

        binding.btAddToMyPlants.setOnClickListener {
            //startActivity(Intent(this, MyPlantsFragment::class.java))
        }

    }
}