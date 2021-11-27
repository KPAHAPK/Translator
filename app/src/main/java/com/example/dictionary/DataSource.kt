package com.example.dictionary

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}