package com.haldun.android.student.studentdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.haldun.android.student.R
import com.haldun.android.student.database.Rate
import com.haldun.android.student.databinding.FragmentStudentDetailsBinding


class StudentDetailsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentStudentDetailsBinding  = DataBindingUtil.inflate(
            inflater, R.layout.fragment_student_details, container, false)

     val viewModel = ViewModelProvider(this).get(StudentDetailsViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.viewmodel = viewModel
        binding.rate=Rate()
        binding.backButton.setOnClickListener {

            this.findNavController().navigate(StudentDetailsFragmentDirections.actionStudentDetailsFragmentToStudentTrackerFragment())

        }
        viewModel.navigateToStudentTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                // Observed state is true.
                this.findNavController().navigate(StudentDetailsFragmentDirections.actionStudentDetailsFragmentToStudentTrackerFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                viewModel.doneNavigating()
            }
        })

        return binding.root

    }


}