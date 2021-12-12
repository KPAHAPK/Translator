package com.example.dictionary.model.viewmodel

import androidx.lifecycle.LiveData
import com.example.dictionary.main.HistoryInteractor
import com.example.dictionary.model.data.AppState
import com.example.dictionary.parseSearchResult
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) : BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun handlerError(error: Throwable) {
        _mutableLiveData.value = AppState.Error(error)
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            _mutableLiveData.postValue(parseLocalSearchResult(interactor.getData(word, isOnline)))
        }
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}
