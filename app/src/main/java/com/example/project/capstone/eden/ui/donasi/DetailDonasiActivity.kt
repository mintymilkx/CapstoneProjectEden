package com.example.project.capstone.eden.ui.donasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.example.project.capstone.eden.R
import com.example.project.capstone.eden.databinding.ActivityDetailDonasiBinding
import org.w3c.dom.Text

class DetailDonasiActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail_donasi)

        val name: TextView = findViewById(R.id.tv_nama_donasi)
        val amount: TextView = findViewById(R.id.tv_jumlah_donasi)
        val photo: ImageView = findViewById(R.id.iv_sample_donasi)
        val organization: TextView = findViewById(R.id.tv_penggalang_dana)

        val bundle: Bundle? = intent.extras
        val donationName = bundle!!.getString("name")
        val donationAmount = bundle.getString("amount")
        val donationImage = bundle.getInt("image")
        val organizationName = bundle.getString("organization")

        name.text = donationName
        amount.text = donationAmount
        photo.setImageResource(donationImage)
        organization.text = organizationName



    }
}