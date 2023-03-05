package com.example.receipebookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class RecipeDetails : AppCompatActivity() {

    private lateinit var tvRecipeId: TextView
    private lateinit var tvRecipeName: TextView
    private lateinit var tvIngredient1: TextView
    private lateinit var tvIngredient2: TextView
    private lateinit var tvIngredient3: TextView
    private lateinit var tvIngredient4: TextView
    private lateinit var tvIngredient5: TextView
    private lateinit var btnEditRecipe: Button
    private lateinit var btnDeleteRecipe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)


        // init tv variables and get ids from XML
        tvRecipeId = findViewById(R.id.tvRecipeId)
        tvRecipeName = findViewById(R.id.tvRecipeName)
        tvIngredient1 = findViewById(R.id.tvIngredient1)
        tvIngredient2 = findViewById(R.id.tvIngredient2)
        tvIngredient3 = findViewById(R.id.tvIngredient3)
        tvIngredient4 = findViewById(R.id.tvIngredient4)
        tvIngredient5 = findViewById(R.id.tvIngredient5)

        btnEditRecipe = findViewById(R.id.btnEditRecipe)
        btnDeleteRecipe = findViewById(R.id.btnDeleteRecipe)

        // Assign variables data from previous intent
        tvRecipeId.text = intent.getStringExtra("recipeId")
        tvRecipeName.text = intent.getStringExtra("recipeName")
        tvIngredient1.text = intent.getStringExtra("ingredient1")
        tvIngredient2.text = intent.getStringExtra("ingredient2")
        tvIngredient3.text = intent.getStringExtra("ingredient3")
        tvIngredient4.text = intent.getStringExtra("ingredient4")
        tvIngredient5.text = intent.getStringExtra("ingredient5")

        // On click function for updating data.
        btnEditRecipe.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("recipeId").toString(),
                intent.getStringExtra("recipeName").toString()
            )
        }

        btnDeleteRecipe.setOnClickListener {
            val dbRef = FirebaseDatabase.getInstance()
                .getReference("Recipes")
                .child(intent.getStringExtra("recipeId").toString())
            val task = dbRef.removeValue()

            task.addOnSuccessListener {
                Toast.makeText(this, "Recipe Deleted Successfully", Toast.LENGTH_LONG).show()
                val intent = Intent(this, FetchActivity::class.java)
                finish()
                startActivity(intent)
            }.addOnFailureListener {error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun openUpdateDialog(recipeId: String, recipeName: String){

        // Create alert dialog box and inflate
        val dialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog,null)
        dialog.setView(dialogView)

        // Get edit text ids from XML
        val etRecipeName = dialogView.findViewById<EditText>(R.id.etRecipeName)
        val etIngredient1 = dialogView.findViewById<EditText>(R.id.etIngredient1)
        val etIngredient2 = dialogView.findViewById<EditText>(R.id.etIngredient2)
        val etIngredient3 = dialogView.findViewById<EditText>(R.id.etIngredient3)
        val etIngredient4 = dialogView.findViewById<EditText>(R.id.etIngredient4)
        val etIngredient5 = dialogView.findViewById<EditText>(R.id.etIngredient5)
        val btnUpdate = dialogView.findViewById<Button>(R.id.btnUpdate)

        // Display current values in dialog
        etRecipeName.setText(intent.getStringExtra("recipeName").toString())
        etIngredient1.setText(intent.getStringExtra("ingredient1").toString())
        etIngredient2.setText(intent.getStringExtra("ingredient2").toString())
        etIngredient3.setText(intent.getStringExtra("ingredient3").toString())
        etIngredient4.setText(intent.getStringExtra("ingredient4").toString())
        etIngredient5.setText(intent.getStringExtra("ingredient5").toString())

        val alertDialog = dialog.create()
        alertDialog.show()

        // On click for save button.
        btnUpdate.setOnClickListener {
            // get database reference
            val dbRef = FirebaseDatabase.getInstance().getReference("Recipes").child(recipeId)
            // create new recipe object based off what is entered in edit text fields.
            val recipeData = Recipe(recipeId, etRecipeName.text.toString(), etIngredient1.text.toString(),
                etIngredient2.text.toString(),etIngredient3.text.toString(),etIngredient4.text.toString(), etIngredient5.text.toString())
            // Set new recipe object to current database reference child.
            dbRef.setValue(recipeData)
            Toast.makeText(applicationContext, "Updated Recipe In Cloud", Toast.LENGTH_LONG).show()

            // Set values in information table to new values.
            tvRecipeName.text = etRecipeName.text.toString()
            tvIngredient1.text = etIngredient1.text.toString()
            tvIngredient2.text = etIngredient2.text.toString()
            tvIngredient3.text = etIngredient3.text.toString()
            tvIngredient4.text = etIngredient4.text.toString()
            tvIngredient5.text = etIngredient5.text.toString()

            alertDialog.dismiss()
        }
    }
}