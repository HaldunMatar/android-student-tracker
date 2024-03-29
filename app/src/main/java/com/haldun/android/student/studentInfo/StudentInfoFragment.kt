package com.haldun.android.student.studentInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.haldun.android.student.R
import com.haldun.android.student.database.Student
import com.haldun.android.student.database.StudentDatabase

import com.haldun.android.student.databinding.FragmentStudentInfoBinding
import com.haldun.android.student.studentTracker.StudentTrackerFragment
import com.haldun.android.student.studentdetails.StudentDetailsFragmentArgs
import com.haldun.android.student.studentdetails.StudentDetailsFragmentDirections


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentStudentInfoBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_student_info, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = StudentDatabase.getInstance(application).studentDatabaseDao

        val args = StudentInfoFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = StudentInfoViewModelFactory(args.studentKey,dataSource)
        val studentInfoViewModel =
                ViewModelProviders.of(
                        this, viewModelFactory).get(StudentInfoViewMode::class.java)


        binding.backStoreButton.setOnClickListener {

            this.findNavController().navigate(StudentInfoFragmentDirections.actionStudentInfoFragmentToStudentTrackerFragment())
        }


        studentInfoViewModel.navigateToStudentTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                // Observed state is true.
                this.findNavController().navigate(StudentInfoFragmentDirections.actionStudentInfoFragmentToStudentTrackerFragment() )
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                studentInfoViewModel.doneNavigating()
            }
        })



        binding.viewmodel = studentInfoViewModel
        binding.setLifecycleOwner(this)
        binding.student= studentInfoViewModel.student.value


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                StudentInfoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}