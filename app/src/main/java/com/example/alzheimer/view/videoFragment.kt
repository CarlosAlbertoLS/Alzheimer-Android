package com.example.alzheimer.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.alzheimer.R
import com.example.alzheimer.databinding.FragmentVideoBinding
import java.security.Permission

class videoFragment() : Fragment() {
    private val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        val video:Button = view.findViewById<Button>(R.id.btnGrabarVideo)
        video.setOnClickListener { checkPermissions() }
        return view
    }

    private fun checkPermissions() {
        val video:Button = (view?.findViewById<Button>(R.id.btnGrabarVideo) ?: "") as Button
        for (permissions in permissions) {
            if (ContextCompat.checkSelfPermission(video.context, permissions) != PackageManager.PERMISSION_GRANTED) {
                requestCameraPermission()
            } else {
                openCamera()
            }
        }
    }

    private fun openCamera() {
        val video:Button = (view?.findViewById<Button>(R.id.btnGrabarVideo) ?: "") as Button
        Toast.makeText(video.context,"Abriendo camara",Toast.LENGTH_SHORT).show()
    }

    private fun requestCameraPermission() {
        val video:Button = (view?.findViewById<Button>(R.id.btnGrabarVideo) ?: "") as Button
        for (permissions in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(video.context as Activity, permissions)
            ) {
                Toast.makeText(video.context, "Permisos rechazados", Toast.LENGTH_SHORT).show()
            } else {
                //Toast.makeText(video.context, permissions,Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(video.context as Activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA),306)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val video:Button = (view?.findViewById<Button>(R.id.btnGrabarVideo) ?: "") as Button
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 306){
            openCamera()
        }else {
            Toast.makeText(video.context,"Los permisos fueron rechazados",Toast.LENGTH_SHORT).show()
        }
    }
}