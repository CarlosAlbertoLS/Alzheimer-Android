package com.example.alzheimer.model.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class DbFirestorePatient {
    private val db = FirebaseFirestore.getInstance()

    fun saveDataUserPatient(email: String, name: String, phoneNumber: String, birthday: String,
                                   weight: String, height: String){
        db.collection("Patients").document(email).set(
            hashMapOf("Name" to name,
            "Phone Number" to phoneNumber,
            "Birthday" to birthday,
            "Weight" to weight,
            "Height" to height)
        )
    }

    fun setDataUserPatient(email: String):String{
        var name: String =""
        db.collection("Patients").document(email).get().addOnSuccessListener {
            name = (it.get("Name") as String?).toString()
        }
        return name
    }
}
