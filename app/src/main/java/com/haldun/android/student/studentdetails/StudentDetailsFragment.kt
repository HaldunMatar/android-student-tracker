package com.haldun.android.student.studentdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.haldun.android.student.R
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

        binding.backButton.setOnClickListener {

            this.findNavController().navigate(StudentDetailsFragmentDirections.actionStudentDetailsFragmentToStudentTrackerFragment())

        }
        return binding.root

    }


}