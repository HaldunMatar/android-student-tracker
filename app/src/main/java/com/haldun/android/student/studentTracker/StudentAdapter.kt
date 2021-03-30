package com.haldun.android.student.studentTracker

import android.util.Log


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.haldun.android.student.database.StudentRate
import com.haldun.android.student.databinding.ListItemStudentBinding


class StudentAdapter(
    val clickListener: StudentEvaluationListener, val studentAddListEvaluationListener:
    StudentAddListEvaluationListener
) : ListAdapter<StudentRate,
        StudentAdapter.ViewHolder>(   SleepNightDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener,studentAddListEvaluationListener  , item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("onCreateViewHolder" , "onCreateViewHolder")
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemStudentBinding)
        : RecyclerView.ViewHolder(binding.root) {


        fun bind(
            studentEvaluationListener: StudentEvaluationListener,
            studentAddListEvaluationListener: StudentAddListEvaluationListener,
            item: StudentRate) {

            binding.studentRate = item
            binding.studentEvaluationListener = studentEvaluationListener

           binding.checkBox.setOnCheckedChangeListener(studentAddListEvaluationListener.onCheckedChangeListener);


         binding.studentAddListEvaluationListener= studentAddListEvaluationListener

            binding.executePendingBindings()

        }



        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemStudentBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}




class SleepNightDiffCallback : DiffUtil.ItemCallback<StudentRate>() {
    override fun areItemsTheSame(oldItem: StudentRate, newItem: StudentRate): Boolean {
        return oldItem.studentId == newItem.studentId
    }

    override fun areContentsTheSame(oldItem: StudentRate, newItem: StudentRate): Boolean {
        return oldItem == newItem
    }
}

class StudentEvaluationListener(val clickListener: (Long) -> Unit,
                              ) {




    fun onClick(student: StudentRate) = clickListener(student.studentId)
}

class StudentInfoListener(val clickListener: (Long) -> Unit) {

    fun onClick(student: StudentRate) = clickListener(student.studentId)
}

class StudentAddListEvaluationListener(val clickListener: (Long,Int) -> Unit

                ,       val     onCheckedChangeListener: CompoundButton.OnCheckedChangeListener


)
{


    fun onClick(studentRate: StudentRate,i: Int ) = clickListener(studentRate.studentId,i)






}
/*

,

 */