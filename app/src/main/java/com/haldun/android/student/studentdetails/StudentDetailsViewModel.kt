package com.haldun.android.student.studentdetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.haldun.android.student.database.Rate
import com.haldun.android.student.database.RateDatabaseDao
import com.haldun.android.student.database.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentDetailsViewModel
(val studentId:Long, val  stringKeys : String  ,val dataSource : RateDatabaseDao , application: Application) :
    AndroidViewModel(application)
{

    private lateinit var  ListKeys: List<String>
    var rate = MutableLiveData<Rate?>()
    init {
        rate.value= Rate()
         ListKeys =  stringKeys.split(",").map{
             it.trim()
         }


        ListKeys.forEach {

            Log.i("stringKey",it.toString())

        }
    }


    private val _navigateToStudentTracker = MutableLiveData<Boolean?>()

    val navigateToStudentTracker: LiveData<Boolean?>
        get() = _navigateToStudentTracker


    fun doneNavigating() {
        _navigateToStudentTracker.value = null

    }

    fun onSetStudentsQuality(rateValue : Int){


        ListKeys.forEach {

            rate.value?.rateValue = rateValue
            rate.value?.rateStudentId = it.toLong()
            Log.i("studentDetailsViewModel" ,"rate int  = "+  rate.value.toString())
            rate.value?.let { onInsert(it) }

        }

        _navigateToStudentTracker.value =true
    }

    fun onSetStudentQuality(rateValue : Int){
        rate.value?.rateValue = rateValue
        rate.value?.rateStudentId = this.studentId
        Log.i("studentDetailsViewModel" ,"rate int  = "+  rate.value.toString())
        rate.value?.let { onInsert(it) }
        _navigateToStudentTracker.value =true
    }



    fun onInsert( rate :  Rate ) {
        viewModelScope.launch {

            Log.i("evaluatiowModel","onInsert")
             insert(rate)



        }
    }

    private suspend fun insert(rate: Rate) {
        withContext(Dispatchers.IO) {
            dataSource.insert(rate)

        }

    }








    fun  studentEvaluation(rate : Rate  ) {
       Log.i("studentDetailsViewModel" ,"rate = "+  rate.toString())



         _navigateToStudentTracker.value =true

    }

}