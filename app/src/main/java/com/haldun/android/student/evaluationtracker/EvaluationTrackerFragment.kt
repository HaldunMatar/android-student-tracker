package com.haldun.android.student.evaluationtracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.haldun.android.student.R
import com.haldun.android.student.database.StudentDatabase
import com.haldun.android.student.databinding.FragmentEvaluationTrackerBinding
import com.haldun.android.student.studentTracker.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EvaluationTrackerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EvaluationTrackerFragment : Fragment() {
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

        val binding :  FragmentEvaluationTrackerBinding =
                DataBindingUtil.inflate(  inflater, R.layout.fragment_evaluation_tracker, container, false)

        val application = requireNotNull(this.activity).application
        val studentDataSource = StudentDatabase.getInstance(application).studentDatabaseDao
        val rateDataSource = StudentDatabase.getInstance(application).rateDatabaseDao

        val viewModelFactory = EvaluationTrackerViewModelFactory(2,rateDataSource,studentDataSource ,application)
        val evaluatioTrackerViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(EvaluationTrackerViewModel::class.java)
        binding.evaluationTrackerViewModel = evaluatioTrackerViewModel
        binding.lifecycleOwner = this


        binding.bakButton.setOnClickListener {

            this.findNavController().
            navigate(EvaluationTrackerFragmentDirections.actionEvaluationTrackerFragmentToStudentTrackerFragment())


        }

        val adapter = EvaluationAdapter( RateListener {
        })

        evaluatioTrackerViewModel.rates.observe(viewLifecycleOwner,{

            Log.i("evaluatiowModel" ," = " + it.size.toString())

            it?.let {
                adapter.submitList(it)
            }

        })

        binding.evaluationsStudentList.adapter = adapter

        // Inflate the layout for this fragment
        return  binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EvaluationTrackerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                EvaluationTrackerFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}