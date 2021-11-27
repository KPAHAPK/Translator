package com.example.dictionary.model.datasource

import com.example.dictionary.model.data.DataModel
import com.example.dictionary.model.datasource.DataSource
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}