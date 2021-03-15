package com.haldun.android.student.evaluationtracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.haldun.android.student.database.RateDatabaseDao
import com.haldun.android.student.database.StudentDatabase
import com.haldun.android.student.database.StudentDatabaseDao
import com.haldun.android.student.studentdetails.StudentDetailsViewModel

class EvaluationTrackerViewModelFactory(
        private val studntKey: Long,
        private val rateDataSource: RateDatabaseDao,
        private val studentDatasource: StudentDatabaseDao,
        private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EvaluationTrackerViewModel::class.java)) {
            return EvaluationTrackerViewModel(studntKey, rateDataSource, studentDatasource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}