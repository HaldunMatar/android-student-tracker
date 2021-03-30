package com.haldun.android.student.evaluationtracker

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haldun.android.student.database.RateDatabaseDao
import com.haldun.android.student.database.Student
import com.haldun.android.student.database.StudentDatabase
import com.haldun.android.student.database.StudentDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EvaluationTrackerViewModel(studntKey: Long, val rateDataSource: RateDatabaseDao,
                                 studentDatabase: StudentDatabaseDao, application: Application)
    :  AndroidViewModel(application)



{


    val rates = rateDataSource.getRatesById(studntKey)
   // val rates = rateDataSource.getAllRates()
    private var student = MutableLiveData<Student?>()





     fun onDelete(it: Long) {
        viewModelScope.launch {

            Log.i("EvaluteViewMode","onDelete")

                delete(it)




        }
    }

    private suspend fun delete(id: Long) {
        withContext(Dispatchers.IO) {
            rateDataSource.delete(id)
        }

    }






}
