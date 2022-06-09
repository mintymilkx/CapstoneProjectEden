package com.example.project.capstone.eden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.project.capstone.eden.fragment.DonasiFragment
import com.example.project.capstone.eden.fragment.GachaFragment
import com.example.project.capstone.eden.fragment.HomeFragment
import com.example.project.capstone.eden.fragment.MyPlantsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {


    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val myPlantsFragment = MyPlantsFragment()
        val gachaFragment = GachaFragment()
        val donasiFragment = DonasiFragment()

        makeCurrentFragment(homeFragment)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.plants -> makeCurrentFragment(myPlantsFragment)
                R.id.scan -> {
                    val intent = Intent(this, ScanActivity::class.java)
                    startActivity(intent)
                }
                R.id.gacha -> makeCurrentFragment(gachaFragment)
                R.id.donasi -> makeCurrentFragment(donasiFragment)
            }

            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()

        }
    }
}