package com.example.project.capstone.eden.ui.scan

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.project.capstone.eden.R
import com.example.project.capstone.eden.activity.data.FirebaseStorage
import com.example.project.capstone.eden.databinding.ActivityCameraBinding
import com.example.project.capstone.eden.ui.tflite.Classifier
import com.github.dhaval2404.imagepicker.ImagePicker

class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding
    private val mInputSize = 150
    private val mModelPath = "PlantModel.tflite"
    private val mLabelPath = "label.txt"
    private lateinit var classifier: Classifier
    private var condition = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        classifierInitialization()
        initUI()


    }

    private fun classifierInitialization() {
        classifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)
    }

    private fun initUI() {
        binding.btnCamera.setOnClickListener {
            captureImageUsingCamera()
        }

        binding.btnUploadImage.setOnClickListener {
            pickImageFromGallery()
        }
        binding.btnScan.setOnClickListener {
            if (condition) {
                findViewById<ImageView>(R.id.iv_place_holder)
                val bitmap = ((binding.ivPlaceHolder as ImageView).drawable as BitmapDrawable).bitmap
                val result = classifier.recognizeImage(bitmap)
                val resultPlant = result.get(0).title


                runOnUiThread {
                    Toast.makeText(this, resultPlant, Toast.LENGTH_SHORT).show()

                    val resultPlant = resultPlant.toString()
                    val intent = Intent(this, PlantDetailAfterScanningActivity::class.java)
                    intent.putExtra("resultPlant", resultPlant)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Please, choose an image!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pickImageFromGallery() {
        ImagePicker.with(this).crop().galleryOnly().start(REQUEST_FROM_GALLERY)
    }

    private fun captureImageUsingCamera() {
        ImagePicker.with(this).crop().cameraOnly().start(REQUEST_FROM_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_FROM_CAMERA || requestCode == REQUEST_FROM_GALLERY) {
            when (requestCode) {

                REQUEST_FROM_CAMERA -> {
                    binding.ivPlaceHolder.setImageURI(data!!.data)
                    data.data?.let { FirebaseStorage().uploadImage(this, it) }
                    condition = true
                }

                REQUEST_FROM_GALLERY -> {
                    binding.ivPlaceHolder.setImageURI(data!!.data)
                    data.data?.let { FirebaseStorage().uploadImage(this, it) }
                    condition = true
                }
            }
        }
    }


    companion object {
        const val REQUEST_FROM_CAMERA = 1001;
        const val REQUEST_FROM_GALLERY = 1002;

    }
}