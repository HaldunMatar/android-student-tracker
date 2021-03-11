package com.haldun.android.student.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface RateDatabaseDao {
    @Insert
    suspend fun insert(rate: Rate)

    @Update
    suspend fun update (rate: Rate)



}