package com.haldun.android.student.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data @Entity(tableName = "rate_table")
 class Rate(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "rate_id")
  var rateId:Long=0L ,
  @ColumnInfo(name = "rate_value")
  var rateValue:Float=-1F  ,

  @ColumnInfo(name = "rate_des")
  var rateDes:String ="  " ,

  @ColumnInfo(name = "rate_student_id")
  var rateStudentId:Long=0 ,

  @ColumnInfo(name = "rate_start_time")
  val rateTime: Long = System.currentTimeMillis(),
){



 }
