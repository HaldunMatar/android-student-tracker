package com.haldun.android.student.studentInfo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haldun.android.student.database.Student
import com.haldun.android.student.database.StudentDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentInfoViewMode(
        private val studentKey: Long = 0L,
        val database: StudentDatabaseDao) : ViewModel() {


     var student = MutableLiveData<Student?>()
    init {
        student.value= Student()
    }

    fun onInsert( std :  Student ) {
        viewModelScope.launch {

           Log.i("StudentInfoViewMode","onInsert")
            student.value?.let { insert(it) }
            getToStudent()


        }
    }

    private suspend fun insert(night: Student) {
        withContext(Dispatchers.IO) {
            database.insert(night)

        }
    }

    private fun getToStudent() {
        viewModelScope.launch {
           val  temp = getToStudentFromDatabase()

            student.value= Student()
            student.value?.studentName=" "

            student.value?.studentGrade=temp?.studentGrade!!

            student.value?.studentSubGrade= temp?.studentGrade!!

        }
    }


    private suspend fun getToStudentFromDatabase(): Student? {
        var student = database.getToStudent()
        return student
    }






}