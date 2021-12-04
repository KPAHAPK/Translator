package com.example.dictionary.ui.base

import com.example.dictionary.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}