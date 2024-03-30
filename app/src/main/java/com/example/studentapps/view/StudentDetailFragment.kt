package com.example.studentapps.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapps.R
import com.example.studentapps.databinding.FragmentStudentDetailBinding
import com.example.studentapps.viewmodel.DetailViewModel
import com.example.studentapps.viewmodel.ListViewModel
import com.example.studentapps.util.loadImage

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

        val studentId = arguments?.getString("studentId") ?: ""
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(studentId)

        observeViewModel()
    }

    private fun observeViewModel() {

        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.txtID.setText(it.id)
            binding.txtName.setText(it.name)
            binding.txtBod.setText(it.dob)
            binding.txtPhone.setText(it.phone)
            binding.imageViewDetail.loadImage(viewModel.studentLD.value?.photoUrl, binding.progressBarDetail)

        })
    }
}
