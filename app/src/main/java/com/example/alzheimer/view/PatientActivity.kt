package com.example.alzheimer.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alzheimer.databinding.ActivityPatientBinding
import java.util.*

class PatientActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editBirthday.setOnClickListener { showDatePikerDialog() }
        intentHomeActivity()
    }

    private fun showDatePikerDialog() {
        val datPiker = DatePikerFragment{day, month, year -> onDaySeleccted(day, month, year)}
        datPiker.show(supportFragmentManager, "datePiker")
    }

    fun onDaySeleccted(day:Int, month:Int, year:Int){
        binding.editBirthday.setText("$day/$month/$year")
    }

    private fun intentHomeActivity() {
        binding.button.setOnClickListener {
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
        }
    }
}