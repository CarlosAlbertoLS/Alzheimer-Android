package com.example.alzheimer.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alzheimer.databinding.ActivityFamilyBinding
import com.example.alzheimer.model.firebase.DbFirestoreAll

class FamilyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFamilyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFamilyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("mail")
        intentHomeActivity(email ?: "")
    }

    private fun intentHomeActivity(email: String) {
        val name = binding.name.text
        val number = binding.editTextPhone.text
        val namePatient = binding.namePatient.text
        val emailPatient = binding.emailPatient.text
        val dbfirestore = DbFirestoreAll()
        binding.buttonRegister.setOnClickListener {
            if (!name.isNullOrEmpty()) {
                val homeIntent = Intent(this, HomeActivity::class.java)
                dbfirestore.saveDataUserFamily(email, name.toString(), number.toString(),
                    namePatient.toString(), emailPatient.toString())
                startActivity(homeIntent)
            }else{
                Toast.makeText(this,"Se deben llena los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}