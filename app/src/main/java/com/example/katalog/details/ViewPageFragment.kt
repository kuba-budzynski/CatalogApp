package com.example.katalog.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.katalog.R
import com.example.katalog.databinding.FragmentViewPageBinding
import com.google.android.material.tabs.TabLayoutMediator

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ViewPageFragment : Fragment() {

    private val args: ViewPageFragmentArgs by navArgs()
    private lateinit var binding: FragmentViewPageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentViewPageBinding.inflate(inflater, container, false)

        val characterInfo = CharacterInfo()
        val characterGallery = CharacterGallery()
        val characterList = CharacterList()

        characterGallery.arguments = bundleOf("images" to args.images, "name" to args.details?.name)
        characterInfo.arguments = bundleOf("details" to args.details)
        characterList.arguments = bundleOf("connections" to args.connections, "name" to args.details?.name)

        val fragmentList = arrayListOf(
            characterInfo,
            characterGallery,
            characterList
        )
        binding.apply {
            viewPager.adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
            TabLayoutMediator(tabLayout, viewPager){ _, _ ->
           }.attach()
        }
        return binding.root
    }
}

