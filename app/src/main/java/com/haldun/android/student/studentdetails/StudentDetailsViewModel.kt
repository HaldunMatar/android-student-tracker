package com.haldun.android.student.studentdetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.haldun.android.student.database.Rate
import com.haldun.android.student.database.RateDatabaseDao
import com.haldun.android.student.database.Student

class StudentDetailsViewModel(val studentId:Long,dataSource : RateDatabaseDao , application: Application) :
    AndroidViewModel(application)
{



    var rate = MutableLiveData<Rate?>()
    init {
        rate.value= Rate()
    }


    private val _navigateToStudentTracker = MutableLiveData<Boolean?>()

    val navigateToStudentTracker: LiveData<Boolean?>
        get() = _navigateToStudentTracker


    fun doneNavigating() {
        _navigateToStudentTracker.value = null

    }



    fun onSetStudentQuality(rateValue : Int){
        rate.value?.rateValue = rateValue
        rate.value?.rateStudentId = this.studentId
        Log.i("studentDetailsViewModel" ,"rate int  = "+  rate.value.toString())

        _navigateToStudentTracker.value =true
    }


 fun  studentEvaluation(rate : Rate  ) {
       Log.i("studentDetailsViewModel" ,"rate = "+  rate.toString())
         _navigateToStudentTracker.value =true

    }

}