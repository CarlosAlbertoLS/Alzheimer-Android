package com.example.alzheimer.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.alzheimer.BuildConfig
import com.example.alzheimer.databinding.ActivityMainBinding
import com.example.alzheimer.model.CreateAccount
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createAccount()
        singIn()
        forggot()
        version()
    }

    private fun version() {
        binding.tVversion.text = "V."+BuildConfig.VERSION_NAME
    }

    private fun forggot() {
        binding.textViewForgot.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Recuperación de contraseña")
            builder.setMessage("Se realizara un proceso de recuperación de contraseña")
            builder.setPositiveButton("Continuar", null)
            builder.setNegativeButton("Cancelar", null)
            builder.show()
        }
    }

    private fun createAccount() {
        binding.btnCreateAccount.setOnClickListener {
            val intent: Intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }
    }

    private fun singIn() {
        title = "Authentication"

        binding.btnSingIn.setOnClickListener {
            if (binding.editTextTextEmailAddress.text.isNotEmpty() && binding.editTextTextPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(binding.editTextTextEmailAddress.text.toString(),
                        binding.editTextTextPassword.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(binding.editTextTextEmailAddress.text.toString())
                        }else {
                            showAlert()
                        }
                    }
            }
        }
    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }
}