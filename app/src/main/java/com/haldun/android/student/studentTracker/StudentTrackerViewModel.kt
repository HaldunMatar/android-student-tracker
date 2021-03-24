package com.haldun.android.student.studentTracker

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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
    val studentsRate = database.getAllStudentsWithRats()




    val EvalList: MutableList<Long> = mutableListOf()        // or arrayListOf




    private fun addStudentTOEvalList(std: Long) {
        EvalList.add(std)
    }

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


    private val _navigateToStudentQuality = MutableLiveData<Student>()
    val navigateToStudentQuality: LiveData<Student>
        get() = _navigateToStudentQuality

    fun doneNavigating() {
        _navigateToStudentQuality.value = null
    }

    private val _navigateToStudentDataQuality = MutableLiveData<Long>()
    val navigateToStudentDataQuality
        get() = _navigateToStudentDataQuality

    fun onStudentEvaluateClicked(id: Long) {


        _navigateToStudentDataQuality.value = id
    }

    fun onStudentDataQualityNavigated() {
        _navigateToStudentDataQuality.value = null
    }

    fun studentAddListEvaluation(l: Long) {


        addStudentTOEvalList(l);

    }


}