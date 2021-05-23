package com.haldun.android.student.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.haldun.android.student.database.Student
import com.haldun.android.student.database.Rate

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


    @Query("SELECT * FROM student_table where  Student_id  = :studntKey")
    suspend fun getToStudent(studntKey: Long): Student?


    @Query("delete  FROM student_table  where   Student_id= :id ")
    abstract fun delete(id: Long)

    @Query("delete  FROM RATE_TABLE  where   rate_student_id= :id ")
       abstract fun deleteRates(id: Long)


   /*@Query("SELECT *,AVG(rate_value) as ratevalue  FROM " +
            "   student_table" + "INNER JOIN rate_table ON       Student_id =  rate_student_id   GROUP BY student_id , student_name ; ")*/

   /* @Query("SELECT  *, AVG(rate_value)   FROM rate_table GROUP BY rate_student_id  ")
    fun getAllStudentsWithRats(): Long*/

   /* @Query("SELECT AVG(rate_value) as ratevalue  FROM rate_table   where rate_student_id= :studntKey     ")
    // @Query("SELECT * FROM rate_table   where rate_student_id= :studntKey   ORDER BY rate_id DESC  ")
    fun   getAverageRateStudentFromDB(studntKey: Long): Long*/

 /*@Query("SELECT " +
            "student_id  as  studentId  ," +
            "student_name as studentName ,  " +
            "student_grade as studentGrade  " +
            "   student_sub_grade as  studentSubGrade " +
            "   student_start_time as studentStartTime " +
            "  rate_id  as rateId" +

            "rate_des as rateDes" +

            "rate_start_time as rateTime  " +
            " rate_value as  rateValue    " +  " FROM student_table "  +
         "   INNER JOIN rate_table ON rate_student_id = Student_id "

    )

    fun getAllStudentsWithRats(): LiveData<List<StudentRate>>*/


    @Query(
            "SELECT  ROUND( AVG(rate_value), 1)  as rateValue    ,  student_id as studentId ,student_name  as studentName  FROM student_table " +
                    " LEFT  JOIN rate_table ON rate_student_id = Student_id  GROUP BY studentId,studentName "
    )
    fun getAllStudentsWithRats(): LiveData<List<StudentRate>>

}