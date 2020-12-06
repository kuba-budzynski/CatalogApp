package com.example.katalog.viewModels

import androidx.lifecycle.ViewModel
import com.example.GetAllToListQuery

class CharacterVM: ViewModel() {

    private var characters: MutableList<GetAllToListQuery.Character> = mutableListOf()

    fun init(char: Array<GetAllToListQuery.Character>){
        for (character in char) {
            characters.add(character)
        }
    }

    fun getCharacters(): MutableList<GetAllToListQuery.Character> {
        return characters
    }

    fun insertCharacter(index: Int, char: GetAllToListQuery.Character){
        characters.add(index, char)
    }

    fun deleteCharacter(c: GetAllToListQuery.Character){
        characters.remove(c)
    }

}