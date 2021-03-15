package com.haldun.android.student.database


import androidx.lifecycle.LiveData
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


    @Query("SELECT * FROM rate_table ORDER BY rate_id DESC")
    fun getAllRates(): LiveData<List<Rate>>


}