package com.haldun.android.student.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="Student_table" )
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    var studentId:Long=0L ,
        @ColumnInfo(name = "student_name")
     var studentName  : String ="" ,
    @ColumnInfo(name = "student_grade")
    var studentGrade  : String  = "" ,
    @ColumnInfo(name = "student_sub_grade")
    var studentSubGrade  : String ="" ,



    @ColumnInfo(name = "student_start_time")
    val studentStartTime: Long = System.currentTimeMillis(),

)
