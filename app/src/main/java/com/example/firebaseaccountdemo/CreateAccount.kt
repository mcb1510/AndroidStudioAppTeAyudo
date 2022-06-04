package com.example.firebaseaccountdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.util.jar.Attributes

class CreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        //Create ActionBar
        val actionBar = supportActionBar
        actionBar!!.title = "Sign In"
        actionBar.setDisplayHomeAsUpEnabled(true)

        //Handle input (texts boxes and button)
        val db = FirebaseFirestore.getInstance()
        val firstName : EditText = findViewById(R.id.FirstName)
        val lastName : EditText = findViewById(R.id.LastName)
        val passWord : EditText = findViewById(R.id.Password)
        val email : EditText = findViewById(R.id.email)
        val userName : EditText = findViewById(R.id.userName)
        val button : Button = findViewById(R.id.button)
        val login2 : Button = findViewById(R.id.login2)
        login2.visibility = View.INVISIBLE

        // When the create account button is pressed do this:
        button.setOnClickListener(){
            val info = HashMap <String, Any>()   // info will save the user's credentials
            info["First Name"] = firstName.text.toString()
            info["Last Name"] = lastName.text.toString()
            info["Password"] = passWord.text.toString()
            info["email"] = email.text.toString()
            info["Username"] = userName.text.toString()

            // We store the credentials in our data base and display a message on the screen
            db.collection("users").document(userName.text.toString()).set(info).addOnCompleteListener(){
                Toast.makeText(MainActivity@this, "Your account has been created",Toast.LENGTH_SHORT).show()
                // We clear all the input
                firstName.text.clear()
                lastName.text.clear()
                passWord.text.clear()
                email.text.clear()
                userName.text.clear()
                login2.visibility = View.VISIBLE // now the user can log in so we show the button
            }.addOnFailureListener(){
                Toast.makeText(MainActivity@this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }

        // if the user press the login button we go to the login activity
        login2.setOnClickListener()
        {
            val intent = Intent(this, Login::class.java )
            startActivity(intent)
        }

    }
}