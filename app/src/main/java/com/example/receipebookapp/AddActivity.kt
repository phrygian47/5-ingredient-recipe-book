package com.example.receipebookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {

    private lateinit var etRecipeName: EditText
    private lateinit var etIngredient1: EditText
    private lateinit var etIngredient2: EditText
    private lateinit var etIngredient3: EditText
    private lateinit var etIngredient4: EditText
    private lateinit var etIngredient5: EditText
    private lateinit var btnSave: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        etRecipeName = findViewById(R.id.etTitle)
        etIngredient1 = findViewById(R.id.etIngredient1)
        etIngredient2 = findViewById(R.id.etIngredient2)
        etIngredient3 = findViewById(R.id.etIngredient3)
        etIngredient4 = findViewById(R.id.etIngredient4)
        etIngredient5 = findViewById(R.id.etIngredient5)
        btnSave = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Recipes")

        btnSave.setOnClickListener {
            saveRecipeData()
        }

    }

    private fun saveRecipeData() {
        // Get values from edit text fields.
        val recipeName = etRecipeName.text.toString()
        val ingredient1 = etIngredient1.text.toString()
        val ingredient2 = etIngredient2.text.toString()
        val ingredient3 = etIngredient3.text.toString()
        val ingredient4 = etIngredient4.text.toString()
        val ingredient5 = etIngredient5.text.toString()

        if (recipeName.isEmpty()){
            etRecipeName.error = "Please enter recipe name"
        }

        if (ingredient1.isEmpty()){
            etIngredient1.error = "Please fill in all the ingredient fields"
        }
        if (ingredient2.isEmpty()){
            etIngredient2.error = "Please fill in all the ingredient fields"
        }
        if (ingredient3.isEmpty()){
            etIngredient3.error = "Please fill in all the ingredient fields"
        }
        if (ingredient4.isEmpty()){
            etIngredient4.error = "Please fill in all the ingredient fields"
        }
        if (ingredient5.isEmpty()){
            etIngredient5.error = "Please fill in all the ingredient fields"
        }

        val recipeId = dbRef.push().key!!

        val recipe = Recipe(recipeId, recipeName, ingredient1, ingredient2,
            ingredient3, ingredient4, ingredient5)
        dbRef.child(recipeId).setValue(recipe).addOnCompleteListener{
            Toast.makeText(this, "Recipe Saved to cloud", Toast.LENGTH_SHORT).show()

            etRecipeName.text.clear()
            etIngredient1.text.clear()
            etIngredient2.text.clear()
            etIngredient3.text.clear()
            etIngredient4.text.clear()
            etIngredient5.text.clear()

        }.addOnFailureListener { error ->
            Toast.makeText(this, "Error ${error.message}", Toast.LENGTH_SHORT).show()
        }
    }
}