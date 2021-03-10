package com.haldun.android.student.studentInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.haldun.android.student.database.StudentDatabaseDao

class StudentInfoViewModelFactory(
        private val studntKey: Long,
        private val dataSource: StudentDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("uncheecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentInfoViewMode::class.java)) {
            return StudentInfoViewMode(studntKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}