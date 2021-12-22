package com.example.repository.room

import androidx.room.*

@Dao
interface HistoryDao {

    @Query("SELECT * FROM historyDB")
    fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM historyDB WHERE word LIKE :word")
    fun getDataByWord(word: String): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(entities: List<HistoryEntity>)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)
}
