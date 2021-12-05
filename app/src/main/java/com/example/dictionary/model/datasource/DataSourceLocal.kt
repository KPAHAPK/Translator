package com.example.dictionary.model.datasource

import com.example.dictionary.model.data.DataModel

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return remoteProvider.getData(word)
    }
}