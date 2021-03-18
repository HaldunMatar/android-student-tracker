

package com.haldun.android.student.studentTracker

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.haldun.android.student.R
import com.haldun.android.student.database.Rate
import com.haldun.android.student.database.Student
import java.text.SimpleDateFormat


@BindingAdapter("studentImage")
fun ImageView.setSleepImage(item: Student?) {


    item?.let {
        setImageResource(when (item.studentId % 10) {
            0L -> R.drawable.ic_sleep_0
           1L -> R.drawable.ic_sleep_1
          3L -> R.drawable.ic_sleep_2
           4L  -> R.drawable.ic_sleep_3
           5L -> R.drawable.ic_sleep_4
           6L -> R.drawable.ic_sleep_5


            else -> R.drawable.ic_sleep_active
        })
    }
}

@BindingAdapter("studentImageRate")
fun ImageView.setRateImage(item: Rate?) {

    Log.i("setRateImage" ," = " + item.toString())
    item?.let {
        setImageResource(when (item.rateValue    % 10) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            3 -> R.drawable.ic_sleep_2
            4  -> R.drawable.ic_sleep_3
            5 -> R.drawable.ic_sleep_4
            6 -> R.drawable.ic_sleep_5


            else -> R.drawable.ic_sleep_active
        })
    }
}

//---------------------------

@BindingAdapter("rateDateString")
fun TextView.setRatDateString(item: Rate?) {


    if (item != null) {
        this.text=   SimpleDateFormat("EEEE MMM-dd-yyyy")
                .format(item.rateTime).toString()
    }



}


