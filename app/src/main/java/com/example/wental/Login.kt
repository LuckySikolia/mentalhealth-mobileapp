package com.example.wental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wental.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //view binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initializing auth object
        firebaseAuth = FirebaseAuth.getInstance()

        //if user doesn't have an account redirect to registration
        binding.loginPageRedirect.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

        //button action
        binding.loginContainedButton.setOnClickListener {
            val email = binding.EmailETlogin.text.toString().trim()
            val pass = binding.PasswordETlogin.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {

                        //send user to main activity ie home-screen
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}