package com.example.dictionary.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.viewmodel.BaseViewModel
import com.example.dictionary.ui.main.Interactor

abstract class BaseActivity<T : AppState, V : Interactor<AppState>> : AppCompatActivity() {
    abstract val viewModel: BaseViewModel<T>
    abstract fun renderData(appState: T)
}