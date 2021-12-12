package com.example.dictionary.di.koin

import androidx.room.Room
import com.example.dictionary.history.HistoryDataBase
import com.example.dictionary.history.HistoryInteractor
import com.example.dictionary.model.data.DataModel
import com.example.dictionary.model.datasource.RetrofitImplementation
import com.example.dictionary.model.datasource.RoomDataBaseImplementation
import com.example.dictionary.model.repository.Repository
import com.example.dictionary.model.repository.RepositoryImplementation
import com.example.dictionary.model.repository.RepositoryLocal
import com.example.dictionary.model.repository.RepositoryLocalImplementation
import com.example.dictionary.history.HistoryViewModel
import com.example.dictionary.model.viewmodel.MainViewModel
import com.example.dictionary.ui.main.MainInteractor
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "historyDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>>() {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<DataModel>>>() {
        RepositoryLocalImplementation(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
