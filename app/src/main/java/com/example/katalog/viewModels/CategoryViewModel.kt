package com.example.katalog.viewModels

import androidx.lifecycle.ViewModel
import com.example.GetAllCategoriesQuery
import com.example.GetAllToListQuery

class CategoryViewModel: ViewModel() {

    private var category: MutableList<GetAllCategoriesQuery.Kategoria> = mutableListOf()

    fun init(char: Array<GetAllCategoriesQuery.Kategoria>){
        for (character in char) {
            category.add(character)
        }
    }

    fun getCharacters(): MutableList<GetAllCategoriesQuery.Kategoria> {
        return category
    }
}