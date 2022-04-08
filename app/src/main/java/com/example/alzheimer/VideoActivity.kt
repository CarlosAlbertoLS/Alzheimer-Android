package com.example.alzheimer

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.CaseMap
import android.icu.text.CaseMap.Title
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.alzheimer.databinding.ActivityVideoBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private val VIDEO_PICK_GALERY_CODE = 100
    private val VIDEO_PICK_CAMERA_CODE = 101
    private val CAMERA_REQUEST_CODE = 102
    private lateinit var  cameraPermissions:Array<String>
    private var videoUri: Uri? = null
    private lateinit var progressDialog: ProgressDialog
    var mediaController: MediaController? = null
    private lateinit var videoView:VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraPermissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val subirFirebase = findViewById<Button>(R.id.btnUploadVideoFirebase)
        val opcionVideo = findViewById<Button>(R.id.btnSeleccionar)
        opcionVideo.setOnClickListener { videoPickDialog() }
        progressDialog = ProgressDialog(opcionVideo.context)
        progressDialog.setTitle("Please wait...")
        progressDialog.setMessage("Uploading video...")
        progressDialog.setCanceledOnTouchOutside(false)
        subirFirebase.setOnClickListener {
            if (videoUri == null){
                Toast.makeText(this,"Selecciona primero un video", Toast.LENGTH_SHORT).show()
            }else {
                upLoadVideoFireBase()
            }
        }
        val procesar = binding.btnProccessVideo
        procesar.setOnClickListener { videoViewController() }
    }

    private fun videoViewController() {
        videoView = binding.videoView
        if (mediaController == null){
            mediaController = MediaController(this)
            mediaController!!.setAnchorView(this.videoView)
        }
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(videoUri)
        videoView.requestFocus()
        videoView.start()
        videoView.setOnCompletionListener { Toast.makeText(this,"end video",Toast.LENGTH_SHORT).show() }
        videoView.setOnErrorListener { mediaPlayer, i, i2 ->
            Toast.makeText(this,"end video",Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun upLoadVideoFireBase() {
        progressDialog.show()
        val timestamp = ""+ System.currentTimeMillis()
        val filePathAndName = "Videos/video_$timestamp.mp4"
        val storageReference = FirebaseStorage.getInstance().getReference(filePathAndName)
        storageReference.putFile(videoUri!!).addOnSuccessListener{ taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isSuccessful);
            val downloadUri = uriTask.result
            if (uriTask.isSuccessful){
                val hashMap = HashMap<String, Any>()
                hashMap["id"] = timestamp
                hashMap["videoUri"] = "$downloadUri"
                val dbReference = FirebaseDatabase.getInstance().getReference("Videos")
                dbReference.child(timestamp).setValue(hashMap)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this,"Video Uploaded",Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener { e ->
                        progressDialog.dismiss()
                        Toast.makeText(this,"${e.message}",Toast.LENGTH_SHORT).show()
                    }
            }
        }.addOnFailureListener { e ->
            progressDialog.dismiss()
            Toast.makeText(this,"${e.message}",Toast.LENGTH_SHORT).show()
        }
    }

    private fun videoPickDialog(){
        val options = arrayOf("Camara", "Galeria")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar video de: ")
            .setItems(options){dialogInterface, i ->
                if (i==0){
                    if (!checkCameraPermissions()){
                        requestCameraPermissions()
                    }else {
                        videoPickCamera()
                    }
                }else {
                    videoPickGallery()
                }
            }.show()
    }

    private fun requestCameraPermissions(){
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE)
    }

    private fun checkCameraPermissions():Boolean{
        val result1 = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        val result2 = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        return result1 && result2
    }

    private fun videoPickGallery(){
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Choose video"),VIDEO_PICK_GALERY_CODE)
    }

    private fun videoPickCamera(){
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent,VIDEO_PICK_CAMERA_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            CAMERA_REQUEST_CODE ->
                if (grantResults.size > 0){
                    val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if (cameraAccepted && storageAccepted){
                        videoPickCamera()
                    }else {
                        Toast.makeText(this,"Permissions denied",Toast.LENGTH_SHORT).show()
                    }
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK){
            if (requestCode == VIDEO_PICK_CAMERA_CODE){
                videoUri == data!!.data
            }else if (requestCode == VIDEO_PICK_GALERY_CODE){
                videoUri = data!!.data
            }
        }else {
            Toast.makeText(this,"Canceled aqui esta el error", Toast.LENGTH_SHORT).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}