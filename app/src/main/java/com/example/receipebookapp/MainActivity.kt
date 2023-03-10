package com.example.receipebookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdd: Button
    private lateinit var btnView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get button ids from XML
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)

        // Add on click functions to each button
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)   // Start add activity
        }

        btnView.setOnClickListener {
            val intent = Intent(this, FetchActivity::class.java)
            startActivity(intent)       // start fetch activity
        }


    }
}