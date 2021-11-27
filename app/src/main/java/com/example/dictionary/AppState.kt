package com.example.dictionary

sealed class AppState{
    data class Success(val data: List<DataModel>?)
    data class Error(val error: Throwable)
    data class Loading(val progress: Int?)
}
