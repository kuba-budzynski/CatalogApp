package com.example.katalog
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.GetAllCategoriesQuery
import com.example.GetAllToListQuery
import com.example.katalog.databinding.ListScreenBinding
import com.example.katalog.recycler.CharacterAdapter
import com.example.katalog.viewModels.CategoryViewModel
import com.example.katalog.viewModels.CharacterVM
import com.example.katalog.viewModels.FavVM
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.*
import com.google.gson.Gson
import kotlinx.coroutines.*

open class BaseActivity : FullScreenActivity() {

    private lateinit var binding: ListScreenBinding
    private val model: CharacterVM by viewModels()
    private val favModel: FavVM by viewModels()
    private val categoryModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val givenCharacters = intent.getStringExtra("characters")
        val givenCategories = intent.getStringExtra("categories")
        val characters = Gson().fromJson(givenCharacters,
                Array<GetAllToListQuery.Character>::class.java)
        val categories = Gson().fromJson(givenCategories,
                Array<GetAllCategoriesQuery.Kategoria>::class.java)

        model.init(characters)
        categoryModel.init(categories)
    }
}