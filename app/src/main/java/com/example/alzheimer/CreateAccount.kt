package com.example.alzheimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alzheimer.databinding.ActivityCreateAccountBinding

class CreateAccount : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}