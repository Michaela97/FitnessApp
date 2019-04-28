package com.example.fitnessapp.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login()
        register()

    }

    fun login() {
        buttonLogin.setOnClickListener {
            val email = emailLogin.text.toString()
            val pwd = pwdLogin.text.toString()


            if (email.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, "Email or Password is not specified", Toast.LENGTH_LONG).show()
            } else {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) return@addOnCompleteListener
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Log.d("LoginActivity", "Failed to login: ${it.message}")
                    }
            }
        }
    }

    fun register() {
        createAccButton.setOnClickListener {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent) }
    }
}
