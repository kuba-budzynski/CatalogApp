package com.example.katalog.recycler

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.GetAllToListQuery
import com.example.GetCharaterDetailsQuery
import com.example.katalog.CardClickListener
import com.example.katalog.R
import com.example.katalog.viewModels.CharacterParcel
import com.example.katalog.viewModels.FavVM
import com.example.katalog.viewModels.ImageParcel
import kotlinx.android.synthetic.main.character_list_element.view.*
import kotlinx.android.synthetic.main.list_image.view.*

class GalleryAdapter(private var images: Array<ImageParcel>)
    : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    inner class GalleryViewHolder(characterView: View): RecyclerView.ViewHolder(characterView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_image, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.itemView.apply {
            galleryImage.load(images[position].url)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}