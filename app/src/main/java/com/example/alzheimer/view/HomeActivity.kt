package com.example.alzheimer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alzheimer.databinding.ActivityHomeBinding
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("email")

        binding.textView2.setText(email).toString()
    }
    fun setDataUserPatient(email: String) {
        db.collection("Patients").document(email).get().addOnSuccessListener {
            binding.textView2.setText(it.get("Name") as String?).toString()
        }
    }
}