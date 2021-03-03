package com.haldun.android.student.studentTracker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.haldun.android.student.R
import com.haldun.android.student.databinding.StudentTrackerFragmentBinding

import com.haldun.android.student.database.StudentDatabase

class StudentTrackerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: StudentTrackerFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.student_tracker_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = StudentDatabase.getInstance(application).studentDatabaseDao
        val viewModelFactory = StudentTrackerViewModelFactory(dataSource, application)
        val sleepTrackerViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(StudentTrackerViewModel::class.java)
        binding.studentTrackerViewModel = sleepTrackerViewModel
        binding.lifecycleOwner = this
        val manager = GridLayoutManager(activity, 3)
        binding.studentList.layoutManager = manager
        val adapter = StudentAdapter()
        binding.studentList.adapter = adapter
        sleepTrackerViewModel.students.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
