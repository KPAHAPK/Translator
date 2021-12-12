package com.example.dictionary.model.datasource

import com.example.dictionary.convertDataModelSuccessToEntity
import com.example.dictionary.history.HistoryDao
import com.example.dictionary.mapHistoryEntityToSearchResult
import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.data.DataModel

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.getAll())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}