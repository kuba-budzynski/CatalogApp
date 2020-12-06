package com.example.katalog.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.katalog.R
import com.example.katalog.databinding.FragmentCharacterGalleryBinding
import com.example.katalog.recycler.GalleryAdapter
import com.example.katalog.viewModels.ImageParcel
import kotlinx.android.synthetic.main.fragment_character_list.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharacterGallery : Fragment() {

    private lateinit var binding: FragmentCharacterGalleryBinding
    private lateinit var images: Array<ImageParcel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterGalleryBinding.inflate(inflater, container, false)
        images = arguments?.getParcelableArray("images") as Array<ImageParcel>
        val adapter = GalleryAdapter(images)
        val layoutManager = LinearLayoutManager(context)

        binding.apply {

            imageRecycler.adapter = adapter
            imageRecycler.layoutManager = layoutManager
            back2.setOnClickListener {
                findNavController().navigate(R.id.action_viewPageFragment2_to_recyclerListFragment)
            }
        }
        return binding.root
    }
}