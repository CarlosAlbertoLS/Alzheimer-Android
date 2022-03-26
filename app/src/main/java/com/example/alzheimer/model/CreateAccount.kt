package com.example.alzheimer.model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alzheimer.view.HomeActivity
import com.example.alzheimer.databinding.ActivityCreateAccountBinding
import com.example.alzheimer.model.firebase.AuthFirebase

class CreateAccount : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    val authfirebase = AuthFirebase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createAccount()
    }

    private fun createAccount() {
        val email = binding.email.text
        val password = binding.password.text
        title = "Authentication"
        binding.btnRegistro.setOnClickListener {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                authfirebase.getDataUser(email.toString(), password.toString())
                val homeIntent = Intent(this, HomeActivity::class.java)
                startActivity(homeIntent)
            }
        }
    }
}