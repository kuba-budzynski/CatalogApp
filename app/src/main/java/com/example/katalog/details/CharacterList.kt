package com.example.katalog.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.katalog.R
import com.example.katalog.databinding.FragmentCharacterListBinding
import com.example.katalog.recycler.ListRecyclerAdapter
import com.example.katalog.viewModels.CharacterParcel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharacterList : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var connections: Array<CharacterParcel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        connections = arguments?.getParcelableArray("connections") as Array<CharacterParcel>
        val name = arguments?.getString("name")
        val filtered = connections.filter{
            it.character.name != name
        }.toTypedArray()

        filtered.shuffle()
        val adapter = ListRecyclerAdapter(filtered)
        val layoutManager = LinearLayoutManager(context)

        binding.apply {
            listRecycler.adapter = adapter
            listRecycler.layoutManager = layoutManager
            back3.setOnClickListener{
                findNavController().navigate(R.id.action_viewPageFragment2_to_recyclerListFragment)
            }
        }
        return binding.root
    }
}