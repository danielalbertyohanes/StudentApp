package com.example.studentapps.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapps.R
import com.example.studentapps.databinding.FragmentStudentDetailBinding
import com.example.studentapps.viewmodel.DetailViewModel
import com.example.studentapps.viewmodel.ListViewModel

class StudentDetailFragment : Fragment() {

    private lateinit var binding: FragmentStudentDetailBinding

    private lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentStudentDetailBinding.inflate( inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer { student ->
            student.let {
                binding.txtID.setText(student.id)
                binding.txtName.setText(student.name)
                binding.txtBod.setText(student.dob)
                binding.txtPhone.setText(student.phone)
            }
        })
    }
}