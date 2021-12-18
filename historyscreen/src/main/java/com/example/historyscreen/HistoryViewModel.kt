package com.example.historyscreen

import androidx.lifecycle.LiveData
import com.example.core.viewmodel.BaseViewModel
import com.example.dictionary.parseLocalSearchResults
import com.example.model.AppState
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
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}
