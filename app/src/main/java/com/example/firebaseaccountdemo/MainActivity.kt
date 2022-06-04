package com.example.firebaseaccountdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.util.jar.Attributes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create ActionBar
        val actionBar = supportActionBar
        actionBar!!.title = "TeAyudo"
        actionBar.setDisplayHomeAsUpEnabled(true)

        // go to the create account activity
        val signupButton : Button = findViewById(R.id.signupButton)
        signupButton.setOnClickListener(){
            val intent = Intent(this, CreateAccount::class.java )
            startActivity(intent)
        }

        // go to the login activity
        val loginButton : Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener(){
            val intent = Intent(this, Login::class.java )
            startActivity(intent)
        }



    }
}