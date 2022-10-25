package com.example.wental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wental.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

class Registration : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    //create firebase authentication object
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //view binding
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initializing auth object
        firebaseAuth = FirebaseAuth.getInstance()

        //if user is registered redirect to login page
        binding.regPageRedirect.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        //button action
        binding.registerContainedButton.setOnClickListener {
            //might have to remove the username feature if work around is not found
           val username = binding.UsernameET.text.toString()
            val email = binding.EmailET.text.toString()
            val pass = binding.PasswordET.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        //send user to login activity
                        val intent = Intent(this, Login::class.java)
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