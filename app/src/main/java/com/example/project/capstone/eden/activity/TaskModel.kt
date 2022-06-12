package com.example.project.capstone.eden.activity

data class TaskModel(
    val activity: String,
    val exp: Int

){
    constructor():this("", 0)
}
