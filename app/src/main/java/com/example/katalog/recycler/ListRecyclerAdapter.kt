package com.example.katalog.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.katalog.R
import com.example.katalog.viewModels.CharacterParcel
import kotlinx.android.synthetic.main.character_list_element.view.*

class ListRecyclerAdapter(private var characters: Array<CharacterParcel>)
    : RecyclerView.Adapter<ListRecyclerAdapter.ListViewHolder>() {

    inner class ListViewHolder(characterView: View): RecyclerView.ViewHolder(characterView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_list_element, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.itemView.apply {
            listName.text = characters[position].character.name
            listCover.load(characters[position].character.cover.url)
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}