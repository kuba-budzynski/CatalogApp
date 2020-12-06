package com.example.katalog.viewModels

import androidx.lifecycle.ViewModel
import com.example.GetAllToListQuery

class FavVM: ViewModel() {

    private var favCharacters: MutableList<GetAllToListQuery.Character> = mutableListOf()

    fun addCharacter(c: GetAllToListQuery.Character){
        favCharacters.add(c)
    }

    fun removeCharacter(c: GetAllToListQuery.Character): Boolean{
        return favCharacters.remove(c)
    }

    fun isFav(c: GetAllToListQuery.Character): Boolean{
        return favCharacters.contains(c)
    }

    fun getCharacters(): MutableList<GetAllToListQuery.Character>{
        return favCharacters
    }
}