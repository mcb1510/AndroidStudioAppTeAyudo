package com.example.firebaseaccountdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text
import java.util.jar.Attributes

class Login : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Create ActionBar
        val actionBar = supportActionBar
        actionBar!!.title = "Log in"
        actionBar.setDisplayHomeAsUpEnabled(true)

        //Handle input (texts boxes and button)
        val db = FirebaseFirestore.getInstance()
        val userName : EditText = findViewById(R.id.userNameLogin)
        val password : EditText = findViewById(R.id.passwordLogin)
        val button : Button = findViewById(R.id.buttonLogin)
        val buttonManage : Button = findViewById(R.id.manage)

        // We do not show the option to manage account at the beginning
        buttonManage.visibility = View.INVISIBLE

        //If the user clicks the login button
        button.setOnClickListener()
        {
            db.collection("users").document(userName.text.toString()).get().addOnSuccessListener { document ->
                if(document.exists()) // we search for the username in our database and if exists we do:
                {
                    val pass : String? = document.getString("Password") // get the password
                    val name : String? = document.getString("First Name") // get the first name
                    if (password.text.toString() == pass) // if the entered password is the same as in the database we log in
                    {
                        // Display a message of log in and sent the information to the edit account activity
                        Toast.makeText(Login@this,"You have logged in!",Toast.LENGTH_SHORT).show()
                        val userFirstName : String = name.toString()
                        val userPassword : String = pass.toString()
                        val intent = Intent(this, EditAccount::class.java ) // send info to another activity
                        intent.putExtra("FIRSTNAME", userFirstName)
                        intent.putExtra("PASS", userPassword)
                        intent.putExtra("USER", userName.text.toString())
                        startActivity(intent)
                        userName.text.clear()
                        password.text.clear()
                    }
                    else
                    {
                        //if the passwords don't match
                        Toast.makeText(Login@this,"Wrong password. Try again.",Toast.LENGTH_LONG).show()
                        password.text.clear()
                    }
                }
                else
                {
                    // if the username entered by the user does not exist
                    Toast.makeText(Login@this,"Error. Username not found, try again.",Toast.LENGTH_LONG).show()
                    userName.text.clear()
                    password.text.clear()
                }
            }
        }

        // if the user successfully logs in we display the manage account button
        // if the user clicks on it we go to the edit account activity
        buttonManage.setOnClickListener()
        {
            if (buttonManage.visibility == View.VISIBLE)
            {
                val intent = Intent(this, EditAccount::class.java )
                startActivity(intent)
            }
        }

    }
}