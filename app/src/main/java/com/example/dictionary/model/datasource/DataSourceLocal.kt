package com.example.dictionary.model.datasource

import com.example.dictionary.model.data.AppState

interface DataSourceLocal<T> :
    DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}