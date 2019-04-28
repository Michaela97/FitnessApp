package com.example.fitnessapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.fitnessapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register()



    }

    private fun register() {
        registerButton.setOnClickListener {
            //            val email = emailEditText.text.toString()
//            val pwd = pwdEditText.text.toString()
//
//            if (email.isEmpty() || pwd.isEmpty()) {
//                Toast.makeText(this, "Email or Password is not specified", Toast.LENGTH_LONG).show()
//            } else {
//                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pwd)
//                    .addOnCompleteListener {
//                        if (!it.isSuccessful) return@addOnCompleteListener
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                        Log.d("RegisterActivity", "Successfully registered. User ID: ${it.result!!.user.uid}")
//                    }
//                    .addOnFailureListener {
//                        Log.d("RegisterActivity", "Failed to registered: ${it.message}")
//                        Toast.makeText(this, "Failed to registered: ${it.message}", Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
