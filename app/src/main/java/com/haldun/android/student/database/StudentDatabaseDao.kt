package com.haldun.android.student.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.haldun.android.student.database.Student

@Dao
interface StudentDatabaseDao {
    @Insert
    suspend fun insert(student: Student)

    @Update
    suspend fun update (student: Student)



    @Query("SELECT * FROM student_table ORDER BY Student_id DESC")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM student_table ORDER BY Student_id DESC LIMIT 1")
      suspend fun getToStudent(): Student?

}