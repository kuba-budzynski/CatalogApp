package com.example.katalog.details

import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.toSpanned
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.katalog.R
import com.example.katalog.databinding.FragmentCharacterInfoBinding
import com.example.katalog.viewModels.CharacterDetails

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharacterInfo : Fragment() {

    private lateinit var binding: FragmentCharacterInfoBinding
    private lateinit var characterDetails: CharacterDetails

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterInfoBinding.inflate(inflater, container, false)
        characterDetails = arguments?.getParcelable<CharacterDetails>("details")!!

        binding.apply {
            description.text = Html.fromHtml(characterDetails.description, Html.FROM_HTML_MODE_COMPACT)
            superName.text = characterDetails.name
            back1.setOnClickListener{
                findNavController().navigate(R.id.action_viewPageFragment2_to_recyclerListFragment)
            }
            coverDetail.scaleType = ImageView.ScaleType.CENTER_CROP
            coverDetail.load(characterDetails.cover)

        }
        return binding.root
    }
}