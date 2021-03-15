package com.haldun.android.student.evaluationtracker

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.haldun.android.student.database.Rate
import com.haldun.android.student.databinding.ListItemRatesStudentBinding





    class EvaluationAdapter(val clickListener: RateListener) : ListAdapter<Rate,
            EvaluationAdapter.ViewHolder>(   SleepNightDiffCallback()) {

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.bind(clickListener, item)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            Log.i("onCreateViewHolder" , "onCreateViewHolder")
            return ViewHolder.from(parent)
        }

        class ViewHolder private constructor(val binding: ListItemRatesStudentBinding)
            : RecyclerView.ViewHolder(binding.root) {

            fun bind(clickListener: RateListener ,item: Rate) {
                binding.rate = item
                binding.clickListener = clickListener
                binding.executePendingBindings()
            }



            companion object {
                fun from(parent: ViewGroup): ViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = ListItemRatesStudentBinding.inflate(layoutInflater, parent, false)

                    return ViewHolder(binding)
                }
            }
        }
    }



    class SleepNightDiffCallback : DiffUtil.ItemCallback<Rate>() {
        override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
            return oldItem.rateId == newItem.rateId
        }

        override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
            return oldItem == newItem
        }
    }

    class RateListener(val clickListener: (rateId: Long) -> Unit) {

        fun onClick(rate: Rate) = clickListener(rate.rateId)
    }