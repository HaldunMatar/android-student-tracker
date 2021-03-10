package com.haldun.android.student.studentdetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.haldun.android.student.database.Rate

class StudentDetailsViewModel(application: Application) : AndroidViewModel(application)
{

    var  note : String = "";
    var valueEvl : Int = 0
    var rateM = Rate()


    private val _navigateToStudentTracker = MutableLiveData<Boolean?>()

    val navigateToStudentTracker: LiveData<Boolean?>
        get() = _navigateToStudentTracker


    fun doneNavigating() {
        _navigateToStudentTracker.value = null

    }






 fun  studentEvaluation(rate : Rate  ) {
       Log.i("studentDetailsViewModel" ,"rate = "+  rate.toString())
         _navigateToStudentTracker.value =true

    }

}