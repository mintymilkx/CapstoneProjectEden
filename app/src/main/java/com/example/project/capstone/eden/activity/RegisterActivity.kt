@file:Suppress("DEPRECATION")

package com.example.project.capstone.eden.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import com.example.project.capstone.eden.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    //progressDialog
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        //configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Create Account..")
        progressDialog.setCanceledOnTouchOutside(false)

        editTextListener()
        register()
    }
    private fun editTextListener() {
        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonRegisterEnable()
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
        binding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonRegisterEnable()
            }

            override fun afterTextChanged(s: Editable) {
            }
        })

        binding.haveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setButtonRegisterEnable() {
        binding.btnRegister.isEnabled =
            binding.edtEmail.text.toString().isNotEmpty() &&
                    binding.edtUsername.text.toString().isNotEmpty() &&
                    binding.edtPassword.text.toString().length >= 6 &&
                    userEmailIsValid(binding.edtEmail.text.toString())
    }
    private fun userEmailIsValid(email: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun register(){
        binding.btnRegister.setOnClickListener{
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val username =  binding.edtUsername.text.toString()
            progressDialog.show()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        //register success
                        progressDialog.dismiss()
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child("username")?.setValue(username)
                        Toast.makeText(this, "Registration success! You can login with your account", Toast.LENGTH_LONG).show()
                        //startActivity(Intent(this, LoginActivity::class.java))
                        finish()

                    } else {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Registration Failed, please try again!", Toast.LENGTH_LONG).show()
                    }
                }
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}