package com.example.dictionary

import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.data.DataModel
import com.example.dictionary.model.data.Meanings

fun parseSearchResult(data: AppState): AppState {
    val newSearchResults = arrayListOf<DataModel>()
    when (data) {
        is AppState.Success -> {
            val searchResults = data.data
            if (!searchResults.isNullOrEmpty()) {
                searchResults.forEach { searchResult ->
                    parseResult(searchResult, newSearchResults)
                }
            }
        }
    }
    return AppState.Success(newSearchResults)
}

fun parseResult(dataModel: DataModel, newListDataModel: ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        newMeanings.forEach { newMeaning ->
            if (newMeaning.translation != null && !newMeaning.translation.text.isNullOrBlank()) {
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