package com.george.firebaseapp.data

import android.R.attr.progress
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.george.firebaseapp.model.User
import com.george.firebaseapp.navigation.ROUTE_HOME
import com.george.firebaseapp.navigation.ROUTE_LOGIN
import com.george.firebaseapp.navigation.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(var navController: NavController, var context: Context) {
    var mAuth: FirebaseAuth

    init {
        mAuth = FirebaseAuth.getInstance()
    }

    //create a function that is going to signup
    fun signup(email: String, pass: String, confpass: String) {
        //check
        if (email.isBlank() || pass.isBlank() || confpass.isBlank()) { //if this is true,
            Toast.makeText(context, "Email and password cannot be blank", Toast.LENGTH_LONG).show()
            return

        } else if (pass != confpass) {
            Toast.makeText(context, "Password do not match", Toast.LENGTH_LONG).show()
            return
        } else {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    val userdata = User(email, pass, mAuth.currentUser!!.uid)
                    val regRef = FirebaseDatabase.getInstance().getReference()
                        .child("Users/" + mAuth.currentUser!!.uid)
                    regRef.setValue(userdata).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(context, "Registered successfully", Toast.LENGTH_LONG)
                                .show()
                            navController.navigate(ROUTE_LOGIN)
                        }
                    }
                } else {
                    navController.navigate(ROUTE_REGISTER)
                }
            }
        }
    }

    fun login(email: String, pass: String) {
        //progress.show()
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            //progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Successfully Logged in", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_HOME)
            } else {
                Toast.makeText(context, "${it.exception!!.message}", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            }
        }

    }
    fun logout() {
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
    }
    fun isloggedin(): Boolean{
        return mAuth.currentUser !=null
    }

}
