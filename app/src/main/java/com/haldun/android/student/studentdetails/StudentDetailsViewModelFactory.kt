package com.haldun.android.student.studentdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.haldun.android.student.database.RateDatabaseDao
import com.haldun.android.student.database.StudentDatabaseDao


class StudentDetailsViewModelFactory (
        private val studntKey: Long,
        private val  stringKeys : String,
        private val dataSource: RateDatabaseDao,
        private val   studentDatabase: StudentDatabaseDao,
        private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("uncheecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentDetailsViewModel::class.java)) {
            return StudentDetailsViewModel(studntKey,stringKeys ,dataSource,studentDatabase,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}