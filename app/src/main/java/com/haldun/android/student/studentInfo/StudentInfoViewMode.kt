package com.haldun.android.student.studentInfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haldun.android.student.database.Student
import com.haldun.android.student.database.StudentDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentInfoViewMode(
        private val studentKey: Long = -1L,
        val database: StudentDatabaseDao) : ViewModel()
{

    var student = MutableLiveData<Student?>()

    private val _navigateToStudentTracker = MutableLiveData<Boolean?>()


    fun doneNavigating() {
        _navigateToStudentTracker.value = null

    }

    val navigateToStudentTracker: LiveData<Boolean?>
        get() = _navigateToStudentTracker

    init {
        student.value= Student()

        if(studentKey !== -1L){

          //  onDelete(studentKey)

            ongetInfoStudent(studentKey)

        }

    }

    private fun ongetInfoStudent(studentKey: Long) {

        viewModelScope.launch {


               getToStudent(studentKey)

        }

    }

    private fun getToStudent(studentKey: Long) {

        viewModelScope.launch {
            val  temp = getToStudentFromDatabase(studentKey)
            student.value= temp




        }

    }

    private suspend fun getToStudentFromDatabase(studentKey: Long): Student? {

        var student = database.getToStudent(studentKey)
        return student

    }

    fun onDelete( std :  Long ) {
        viewModelScope.launch {
            Log.i("StudentInfoViewMode","onDelete" +std.toString())
            delete(std)
        }

    }


    private suspend fun delete(std :  Long) {
        withContext(Dispatchers.IO) {
            database.deleteRates(std)
            database.delete(std)


        }
        _navigateToStudentTracker.value =true
    }

    fun onInsert( std :  Student ) {
        viewModelScope.launch {

           Log.i("StudentInfoViewMode","onInsert")
            student.value?.let {

                insert(it)

                   }
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

            student.value?.studentSubGrade= temp?.studentSubGrade!!

        }
    }


    private suspend fun getToStudentFromDatabase(): Student? {
        var student = database.getToStudent()
        return student
    }






}