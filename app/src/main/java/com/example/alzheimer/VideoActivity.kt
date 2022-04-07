package com.example.alzheimer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.alzheimer.databinding.ActivityVideoBinding
import com.example.alzheimer.view.HomeActivity
import com.example.alzheimer.view.videoFragment

class VideoActivity : AppCompatActivity() {
    private val REQUEST_VIDEO_CAPTURE = 1
    private lateinit var binding: ActivityVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val captureVideo = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(captureVideo, REQUEST_VIDEO_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            val videoUri: Uri? = data?.getData()
            binding.videoView.setVideoURI(videoUri)
            binding.videoView.start()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragmentVideoIntent = Intent(this, HomeActivity::class.java)
        startActivity(fragmentVideoIntent)
        VideoActivity().finish()
    }
}