package com.haldun.android.student.studentTracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haldun.android.student.database.Student
import com.haldun.android.student.database.StudentDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentTrackerViewModel(val database: StudentDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    val students = database.getAllStudents()
    private var student = MutableLiveData<Student?>()

    fun onInsert() {
        viewModelScope.launch {
            val studenttemp = Student();
            insert(studenttemp)
        }
    }

    private suspend fun insert(night: Student) {
        withContext(Dispatchers.IO) {
            database.insert(night)
        }
    }




}