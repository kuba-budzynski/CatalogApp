package com.example.katalog.recycler

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.GetAllToListQuery
import com.example.katalog.CardClickListener
import com.example.katalog.GetData
import com.example.katalog.R
import com.example.katalog.viewModels.CharacterParcel
import com.example.katalog.viewModels.FavVM

class CharacterAdapter(
        private var favModel: FavVM,
        private val onCardClick: CardClickListener,
        private val getData: GetData
        ) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(characterView: View): RecyclerView.ViewHolder(characterView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val char = getData.data()[position]
        val name = holder.itemView.findViewById<TextView>(R.id.name)
        val category = holder.itemView.findViewById<TextView>(R.id.category)
        val cover = holder.itemView.findViewById<ImageView>(R.id.cover)
        val fav = holder.itemView.findViewById<Button>(R.id.fav)

        cover.scaleType = ImageView.ScaleType.CENTER_CROP
        cover.load(getData.data()[position].cover.url)

        name.text = getData.data()[position].name
        category.text = getData.data()[position].kategoria?.kategoria
        category.setTextColor(Color.parseColor(getData.data()[position].kategoria?.color?.hex.toString()))

        if(favModel.isFav(char)) fav.setBackgroundResource(chooseColorDrawable(char.kategoria?.kategoria))
        else fav.setBackgroundResource(R.drawable.ic_baseline_shadow_24)

        fav.setOnClickListener{
            if(!favModel.isFav(char)){
                favModel.addCharacter(char)
                fav.setBackgroundResource(chooseColorDrawable(char.kategoria?.kategoria))
            }
            else{
                favModel.removeCharacter(char)
                fav.setBackgroundResource(R.drawable.ic_baseline_shadow_24)
            }
        }
        holder.itemView.setOnClickListener{
            onCardClick.onCardClick(char.name)
        }
    }

    private fun chooseColorDrawable(category: String?): Int{
        return when(category){
            "Superhero" -> R.drawable.ic_baseline_favorite_superhero_24
            "Supervillain" -> R.drawable.ic_baseline_favorite_supervillain_24
            "Complicated" -> R.drawable.ic_baseline_favorite_complicated_24
            else -> R.drawable.ic_baseline_shadow_24
        }
    }

    override fun getItemCount(): Int {
        return getData.data().size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}