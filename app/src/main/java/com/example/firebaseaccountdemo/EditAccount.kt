package com.example.firebaseaccountdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore


class EditAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_account)

        //Create ActionBar
        val actionBar = supportActionBar
        actionBar!!.title = "Manage Account"
        actionBar.setDisplayHomeAsUpEnabled(true)

        //Handle input (texts boxes and button)
        val db = FirebaseFirestore.getInstance()
        val current : EditText = findViewById(R.id.currentP)
        val newP : EditText = findViewById(R.id.newP)
        val changeP : Button = findViewById(R.id.changePassword)
        var strName : String? = intent.getStringExtra("FIRSTNAME") // get info from login activity
        var strPass : String? = intent.getStringExtra("PASS") // get info from login activity
        var strUser : String? = intent.getStringExtra("USER") // get info from login activity
        val greeting : TextView = findViewById(R.id.greeting)
        val signOut : Button = findViewById(R.id.signout)
        val mando : ImageView = findViewById(R.id.mando)

        mando.visibility = View.INVISIBLE // We do not want to see mando until the end
        // display message of welcome with the user's first name
        greeting.text = "Hello " + strName.toString() + "!"

        // if the change password button is press
        changeP.setOnClickListener()
        { //if passwords match we update the password in the database and display a message
            if (current.text.toString() == strPass.toString()) {
                db.collection("users").document(strUser.toString()).update("Password", newP.text.toString())
                current.text.clear()
                newP.text.clear()
                Toast.makeText(EditAccount@ this, "You have changed your password!", Toast.LENGTH_SHORT).show()
            }
            // if the passwords do not match we display an error message
            else {
                Toast.makeText(EditAccount@ this, "Wrong current password. Try again.", Toast.LENGTH_LONG).show()
                current.text.clear()
                newP.text.clear()
            }
        }

        // If the user clicks on sign out button mando comes for my A+
        signOut.setOnClickListener()
        {
            changeP.visibility = View.INVISIBLE
            signOut.visibility = View.INVISIBLE
            mando.visibility = View.VISIBLE
        }

    }
}

