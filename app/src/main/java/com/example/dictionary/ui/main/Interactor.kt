package com.example.dictionary.ui.main

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}