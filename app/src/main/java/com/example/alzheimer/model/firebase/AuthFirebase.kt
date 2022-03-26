package com.example.alzheimer.model.firebase

import android.app.AlertDialog
import com.example.alzheimer.model.CreateAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthFirebase {
    private lateinit var auth: FirebaseAuth

    @Override
    protected fun onCreate() {
        auth = Firebase.auth
    }

    fun getDataUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful){
                    showAlert()
                }
            }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(CreateAccount())
        builder.setTitle("ERROR")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}