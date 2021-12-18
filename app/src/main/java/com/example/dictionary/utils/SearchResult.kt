package com.example.dictionary.utils

import com.example.model.AppState
import com.example.model.DataModel
import com.example.model.Meanings

fun parseOnlineSearchResult(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, true))
}

fun parseLocalSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, false))
}

fun mapResult(appState: AppState, isOnline: Boolean): List<DataModel> {
    val newSearchResult = arrayListOf<DataModel>()
    when (appState) {
        is AppState.Success -> {
            getSuccessResultData(appState, isOnline, newSearchResult)
        }
    }
    return newSearchResult
}

fun getSuccessResultData(
    appState: AppState.Success,
    isOnline: Boolean,
    newDataModel: java.util.ArrayList<DataModel>,
) {
    val dataModels: List<DataModel> = appState.data as List<DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModel)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModel.add(DataModel(searchResult.text, searchResult.meanings))
            }
        }
    }
}

fun parseOnlineResult(dataModel: DataModel, newDataModel: java.util.ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings!!) {
            if (meaning.translation != null && !meaning.translation!!.text.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModel.add(DataModel(dataModel.text, newMeanings))
        }
    }
}

fun parseResult(dataModel: DataModel, newListDataModel: ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        newMeanings.forEach { newMeaning ->
            if (newMeaning.translation != null && !newMeaning.translation!!.text.isNullOrBlank()) {
                newMeanings.add(Meanings(newMeaning.translation, newMeaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newListDataModel.add(DataModel(dataModel.text, newMeanings))
        }
    }
}

fun convertMeaningsToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.text, ", ")
        } else {
            meaning.translation?.text
        }
    }
    return meaningsSeparatedByComma
}