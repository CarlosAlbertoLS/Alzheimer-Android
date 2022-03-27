package com.example.alzheimer.model.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class DbFirestoreAll {
    private val db = FirebaseFirestore.getInstance()

    fun saveDataUserPatient(email: String, name: String, phoneNumber: String, birthday: String,
                                   weight: String, height: String){
        db.collection("Patients").document(email).set(
            hashMapOf("Name" to name,
            "Phone Number" to phoneNumber,
            "Birthday" to birthday,
            "Weight" to weight+"Kgr",
            "Height" to height+"Mts")
        )
    }

    fun saveDataUserDoctorAndNurse(email: String, name: String, phoneNumber: String, speciality: String,
                            cedula: String,){
        db.collection("Doctors and Nurses").document(email).set(
            hashMapOf("Name" to name,
                "Phone Number" to phoneNumber,
                "Speciality" to speciality,
                "Cedula" to cedula)
        )
    }
}
