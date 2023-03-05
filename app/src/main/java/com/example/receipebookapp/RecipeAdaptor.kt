package com.example.receipebookapp

import android.animation.PropertyValuesHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdaptor (private val recipeList: ArrayList<Recipe>) :
    RecyclerView.Adapter<RecipeAdaptor.ViewHolder>(){

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRecipe = recipeList[position]
        holder.tvRecipeName.text = currentRecipe.recipeName
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class ViewHolder(itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvRecipeName : TextView = itemView.findViewById(R.id.tvRecipeName)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}