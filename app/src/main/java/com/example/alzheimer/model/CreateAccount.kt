package com.example.alzheimer.model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.example.alzheimer.view.HomeActivity
import com.example.alzheimer.databinding.ActivityCreateAccountBinding
import com.example.alzheimer.model.firebase.AuthFirebase
import com.example.alzheimer.view.DoctorAndNurseActivity
import com.example.alzheimer.view.PatientActivity

class CreateAccount : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: ActivityCreateAccountBinding
    private val authfirebase = AuthFirebase()
    var opcionIntent: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createAccount()
        binding.radioGrup.setOnCheckedChangeListener(this)
    }

    private fun createAccount() {
        val email = binding.email.text
        val password = binding.password.text
        title = "Authentication"
        binding.btnRegistro.setOnClickListener {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                authfirebase.getDataUser(email.toString(), password.toString())
                Toast.makeText(this, "$opcionIntent", Toast.LENGTH_SHORT).show()
                when (opcionIntent){
                    1-> {
                        showPatientActivity(email.toString())
                    }
                    3,4 ->{
                        showDoctorAndNurseActivity(email.toString())
                    }
                }
            }
        }
    }

    private fun showDoctorAndNurseActivity(email: String) {
        val homeIntent = Intent(this, DoctorAndNurseActivity::class.java).apply {
            putExtra("mail", email)
        }
        startActivity(homeIntent)
    }

    override fun onCheckedChanged(p0: RadioGroup?, idRadio: Int) {
        when (idRadio){
            binding.radioButtonPatient.id -> {
                opcionIntent = 1
            }
            binding.radioButtonFamily.id -> {
                Toast.makeText(this, "Family", Toast.LENGTH_SHORT).show()
                opcionIntent = 2
            }
            binding.radioButtonDoctor.id -> {
                Toast.makeText(this, "Doctor", Toast.LENGTH_SHORT).show()
                opcionIntent = 3
            }
            binding.radioButtonNurse.id -> {
                Toast.makeText(this, "Nurse", Toast.LENGTH_SHORT).show()
                opcionIntent = 4
            }
        }
    }

    fun showPatientActivity(email: String){
        val homeIntent = Intent(this, PatientActivity::class.java).apply {
            putExtra("mail", email)
        }
        startActivity(homeIntent)
    }
}