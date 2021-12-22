package com.example.repository

import com.example.model.AppState
import com.example.model.DataModel
import com.example.repository.room.HistoryEntity


fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val searchResult = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(DataModel(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchReuslt = appState.data
            if (searchReuslt.isNullOrEmpty() || searchReuslt[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchReuslt[0].text!!, null)
            }
        }
        else -> null
    }
}