package com.example.dictionary.model.datasource

import com.example.dictionary.convertDataModelSuccessToEntity
import com.example.dictionary.db.HistoryDao
import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.data.DataModel

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return historyDao.getAll().map { DataModel(it.word, null) }
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}