package com.example.project.capstone.eden.ui.task

data class TaskModel(
    val activity: String,
    val exp: Int

){
    constructor():this("", 0)
}
