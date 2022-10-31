package com.example.todolists

//primary purpose of data class is to hold data
data class Todo (
    val title: String,
    var isChecked: Boolean = false
)