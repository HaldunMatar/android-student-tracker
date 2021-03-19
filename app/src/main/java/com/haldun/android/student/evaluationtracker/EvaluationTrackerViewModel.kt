package com.haldun.android.student.evaluationtracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haldun.android.student.database.RateDatabaseDao
import com.haldun.android.student.database.Student
import com.haldun.android.student.database.StudentDatabase
import com.haldun.android.student.database.StudentDatabaseDao

class EvaluationTrackerViewModel(studntKey: Long, rateDataSource: RateDatabaseDao,
                                 studentDatabase: StudentDatabaseDao, application: Application)
    :  AndroidViewModel(application)



{
    val rates = rateDataSource.getRatesById(studntKey)
   // val rates = rateDataSource.getAllRates()
    private var student = MutableLiveData<Student?>()






}
