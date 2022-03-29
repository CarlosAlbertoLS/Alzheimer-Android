package com.example.alzheimer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alzheimer.databinding.ActivityFamilyBinding

class FamilyActivity : AppCompatActivity() {
    private lateinit var bindin: ActivityFamilyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindin = ActivityFamilyBinding.inflate(layoutInflater)
        setContentView(bindin.root)

    }
}