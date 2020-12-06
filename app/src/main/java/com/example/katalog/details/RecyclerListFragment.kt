package com.example.katalog.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.example.GetAllToListQuery
import com.example.GetCharaterDetailsQuery
import com.example.katalog.CardClickListener
import com.example.katalog.GetData
import com.example.katalog.R
import com.example.katalog.apolloClient
import com.example.katalog.databinding.FragmentRecyclerListBinding
import com.example.katalog.recycler.CharacterAdapter
import com.example.katalog.viewModels.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RecyclerListFragment : Fragment(), CardClickListener, GetData {

    private lateinit var binding: FragmentRecyclerListBinding
    private val model: CharacterVM by activityViewModels()
    private val favModel: FavVM by activityViewModels()
    private val categoryViewModel: CategoryViewModel by activityViewModels()
    private lateinit var adapter: CharacterAdapter
    private var deletedCharacterMessage: String = ""
    private lateinit var layoutManager: LinearLayoutManager
    private var currentPosition: Int = 0
    private var dropDownItems: MutableList<String> = mutableListOf("Default", "Favourites")

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecyclerListBinding.inflate(inflater, container, false)
        adapter = CharacterAdapter(favModel, this, this)
        layoutManager = LinearLayoutManager(context)

        for (cat in categoryViewModel.getCharacters()) {
            dropDownItems.add(cat.kategoria)
        }
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.spinner_item, dropDownItems)

        binding.apply {
            spinner.adapter = arrayAdapter
            recycler.adapter = adapter
            recycler.layoutManager = layoutManager
            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recycler)
            scrollTop.setOnClickListener{
                recycler.smoothScrollToPosition(0)
            }

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    currentPosition = position
                    adapter.notifyDataSetChanged()
                    if(data().isEmpty()){
                        binding.apply {
                            val text = when(dropDownItems[position]){
                                "Default" -> "No characters to show"
                                "Favourites" -> "No favourites characters"
                                "Superhero" -> "No superheros"
                                "Supervillain" -> "No supervillains"
                                "Complicated" -> "No complicated characters"
                                else -> "ERROR"
                            }
                            val snack = Snackbar.make(recycler, text, Snackbar.LENGTH_LONG)
                            snack.show()
                        }
                    }
                }
            }
        }
        return binding.root
    }

    override fun data(): MutableList<GetAllToListQuery.Character> {
        return when(dropDownItems[currentPosition]) {
            "Default" -> model.getCharacters()
            "Favourites" -> model.getCharacters().filter {
                    (favModel.getCharacters()).contains(it)
                }.toMutableList()
            else -> model.getCharacters().filter {
                it.kategoria?.kategoria.equals(dropDownItems[currentPosition])
            }.toMutableList()
        }
    }

    override fun onCardClick(name: String) {
        var character = GlobalScope.async{
            withTimeoutOrNull(5000){
                val response = apolloClient.query(GetCharaterDetailsQuery(Input.fromNullable(name))).await()
                return@withTimeoutOrNull response.data
            }
        }
        runBlocking {
            val char = character.await()
            val characterDetails = CharacterDetails(char?.character?.name, char?.character?.description?.html, char?.character?.cover?.url,
                    char?.character?.kategoria?.kategoria, char?.character?.kategoria?.color?.hex.toString())
            val characterImages = char?.character?.images?.map {
                ImageParcel(char.character.name, it.url)
            }
            val characterConnections = char?.characters?.map{CharacterParcel(it)}!!.toTypedArray()
            val action = RecyclerListFragmentDirections.actionRecyclerListFragmentToViewPageFragment2(characterDetails, characterImages?.toTypedArray()!!, characterConnections)
            findNavController().navigate(action)
        }
    }

    private val itemTouchHelperCallback =
        object :
            ItemTouchHelper.SimpleCallback(0,
                    ItemTouchHelper.LEFT) {
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                var char = data()[position]
                deletedCharacterMessage = "Deleted ${char.name}"

                model.deleteCharacter(char)
                val wasFav = favModel.getCharacters().contains(char)
                favModel.removeCharacter(char)
                adapter.notifyItemRemoved(position)

                binding.apply {
                    val snack = Snackbar.make(recycler, deletedCharacterMessage, Snackbar.LENGTH_LONG)
                    snack.setAction("Undo", View.OnClickListener {
                        model.insertCharacter(position, char)
                        if (wasFav) favModel.addCharacter(char)
                        adapter.notifyItemInserted(position)
                    })
                    snack.show()
                }
            }
        }
}