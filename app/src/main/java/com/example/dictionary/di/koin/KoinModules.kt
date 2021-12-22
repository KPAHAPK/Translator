package com.example.dictionary.di.koin

import androidx.room.Room
import com.example.dictionary.ui.main.MainInteractor
import com.example.dictionary.ui.main.MainViewModel
import com.example.dictionary.ui.screens.MainActivity
import com.example.historyscreen.HistoryActivity
import com.example.historyscreen.HistoryInteractor
import com.example.historyscreen.HistoryViewModel
import com.example.model.DataModel
import com.example.repository.*
import com.example.repository.room.HistoryDataBase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "historyDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryLocalImplementation(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val historyScreen = module {
    scope (named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}
