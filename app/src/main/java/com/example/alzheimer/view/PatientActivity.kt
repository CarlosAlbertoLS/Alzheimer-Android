package com.example.alzheimer.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alzheimer.databinding.ActivityPatientBinding
import com.example.alzheimer.model.firebase.DbFirestorePatient

class PatientActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editBirthday.setOnClickListener { showDatePikerDialog() }

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("mail")
        intentHomeActivity(email ?: "")
    }

    private fun showDatePikerDialog() {
        val datPiker = DatePikerFragment{day, month, year -> onDaySeleccted(day, month, year)}
        datPiker.show(supportFragmentManager, "datePiker")
    }

    fun onDaySeleccted(day:Int, month:Int, year:Int){
        binding.editBirthday.setText("$day/$month/$year")
    }

    private fun intentHomeActivity(email: String) {
        val name = binding.editName.text
        val number = binding.editTextTextPersonName3.text
        val birthday = binding.editBirthday.text
        val weight = binding.editTextTextPersonName5.text
        val height = binding.editTextTextPersonName6.text
        val dbfirestore = DbFirestorePatient()
        binding.button.setOnClickListener {
            if (!name.isNullOrEmpty()) {
                val homeIntent = Intent(this, HomeActivity::class.java)
                Toast.makeText(this, "$birthday", Toast.LENGTH_SHORT).show()
                dbfirestore.saveDataUserPatient(email, name.toString(), number.toString(),
                    birthday.toString(), weight.toString(), height.toString())
                startActivity(homeIntent)
            }else{
                Toast.makeText(this,"Se deben llena los campos",Toast.LENGTH_SHORT).show()
            }
        }
    }
}