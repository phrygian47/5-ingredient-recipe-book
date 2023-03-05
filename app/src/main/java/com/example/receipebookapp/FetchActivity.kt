package com.example.receipebookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class FetchActivity : AppCompatActivity() {

    private lateinit var recipeView: RecyclerView
    private lateinit var tvFetchingData: TextView
    private lateinit var recipeList: ArrayList<Recipe>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)

        recipeView = findViewById(R.id.rvRecipes)
        tvFetchingData = findViewById(R.id.tvFetchingData)
        recipeView.layoutManager = LinearLayoutManager(this)
        recipeView.setHasFixedSize(true)

        recipeList = arrayListOf<Recipe>()

        fetchRecipeData()

    }

    private fun fetchRecipeData() {
        recipeView.visibility = View.GONE
        tvFetchingData.visibility= View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Recipes")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                recipeList.clear()
                if(snapshot.exists()){
                    for(recipeSnap in snapshot.children){
                        val recipeData = recipeSnap.getValue(Recipe::class.java)
                        recipeList.add(recipeData!!)
                    }
                    val tempAdaptor = RecipeAdaptor(recipeList)
                    recipeView.adapter = tempAdaptor

                    tempAdaptor.setOnItemClickListener(object : RecipeAdaptor.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchActivity, RecipeDetails::class.java)

                            intent.putExtra("recipeId", recipeList[position].recipeId)
                            intent.putExtra("recipeName", recipeList[position].recipeName)
                            intent.putExtra("ingredient1", recipeList[position].ingredient1)
                            intent.putExtra("ingredient2", recipeList[position].ingredient2)
                            intent.putExtra("ingredient3", recipeList[position].ingredient3)
                            intent.putExtra("ingredient4", recipeList[position].ingredient4)
                            intent.putExtra("ingredient5", recipeList[position].ingredient5)
                            startActivity(intent)
                        }

                    })

                    recipeView.visibility = View.VISIBLE
                    tvFetchingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}