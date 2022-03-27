package com.example.alzheimer.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alzheimer.databinding.ActivityDoctorAndNurseBinding
import com.example.alzheimer.model.firebase.DbFirestoreAll

class DoctorAndNurseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorAndNurseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorAndNurseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("mail")
        intentHomeActivity(email ?: "")
    }

    private fun intentHomeActivity(email: String) {
        val name = binding.name.text
        val number = binding.number.text
        val speciality = binding.speciality.text
        val cedula = binding.cedula.text
        val dbfirestore = DbFirestoreAll()
        binding.button2.setOnClickListener {
            if (!name.isNullOrEmpty()) {
                val homeIntent = Intent(this, HomeActivity::class.java)
                dbfirestore.saveDataUserDoctorAndNurse(email, name.toString(),
                    number.toString(), speciality.toString(), cedula.toString())
                startActivity(homeIntent)
            }else{
                Toast.makeText(this,"Se deben llena los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}