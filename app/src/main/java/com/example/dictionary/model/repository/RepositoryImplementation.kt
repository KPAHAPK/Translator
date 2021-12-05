package com.example.dictionary.model.repository

import com.example.dictionary.model.data.DataModel
import com.example.dictionary.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}