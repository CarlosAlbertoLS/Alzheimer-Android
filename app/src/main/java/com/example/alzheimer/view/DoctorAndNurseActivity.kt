package com.example.alzheimer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alzheimer.databinding.ActivityDoctorAndNurseBinding

class DoctorAndNurseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorAndNurseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorAndNurseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}