package com.example.dictionary.ui.main

import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.data.DataModel
import com.example.dictionary.model.repository.Repository
import com.example.dictionary.model.repository.RepositoryLocal

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>,
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}