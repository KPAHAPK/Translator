package com.example.dictionary.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.model.data.AppState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
) : ViewModel() {

    open fun getData(word: String, isOnline: Boolean): LiveData<T> =liveDataForViewToObserve

    override fun onCleared() {
        compositeDisposable.clear()
    }

}