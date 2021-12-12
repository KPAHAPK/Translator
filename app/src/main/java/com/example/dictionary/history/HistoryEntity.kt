package com.example.dictionary.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "historyDB", indices = (arrayOf(Index(value = arrayOf("word"), unique = true))))
class HistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "word")
    var word: String,
    @ColumnInfo(name = "description")
    var description: String?
){}
