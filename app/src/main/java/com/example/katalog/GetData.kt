package com.example.katalog

import com.example.GetAllToListQuery

interface GetData {
    fun data(): MutableList<GetAllToListQuery.Character>
}