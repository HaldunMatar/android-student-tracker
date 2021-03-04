

package com.haldun.android.student.studentTracker

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.haldun.android.student.R
import com.haldun.android.student.database.Student


@BindingAdapter("studentImage")
fun ImageView.setSleepImage(item: Student?) {
    item?.let {
        setImageResource(when (item.studentId) {
            item.studentId % 10 -> R.drawable.ic_sleep_0
            item.studentId % 10 -> R.drawable.ic_sleep_1
            item.studentId % 10 -> R.drawable.ic_sleep_2
            item.studentId % 10 -> R.drawable.ic_sleep_3
            item.studentId % 10 -> R.drawable.ic_sleep_4
            item.studentId % 10 -> R.drawable.ic_sleep_5
            item.studentId % 10 -> R.drawable.ic_sleep_2
            item.studentId % 10 -> R.drawable.ic_sleep_3
            item.studentId % 10 -> R.drawable.ic_sleep_4

            else -> R.drawable.ic_sleep_active
        })
    }
}
