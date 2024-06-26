package com.example.studentapps.view

import android.content.Context

import android.os.Bundle
import android.util.Log
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
import java.util.concurrent.TimeUnit
import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import com.example.studentapps.model.Student
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


class StudentDetailFragment : Fragment(), ButtonUpdateClickListener {

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
        binding.listener=this

//        val shared   Preferences = requireContext().getSharedPreferences("student_prefs", Context.MODE_PRIVATE)
//        val studentId = sharedPreferences.getString

        //buat ini gara" pas di pencet button detail lgsg force close gara" picasso data binding
        binding.student = Student("","","","","https://randomuser.me/api/portraits/men/74.jpg")

        val studentId = arguments?.getString("studentId")
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(studentId.toString())

        observeViewModel()
    }


    private fun observeViewModel() {

        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.student=it

//            binding.txtID.setText(it.id)
//            binding.txtName.setText(it.name)
//            binding.txtBod.setText(it.dob)
//            binding.txtPhone.setText(it.phone)
//            binding.imageViewDetail.loadImage(viewModel.studentLD.value?.photoUrl, binding.progressBarDetail)
//            var student = it

//            binding.btnUpdate?.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_person_add_24)
//                    }
//            }
        })
    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(context,"Success Update - " + v.tag.toString(), Toast.LENGTH_SHORT).show()
        Observable.timer(5,TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages","five seconds")
                MainActivity.showNotification(v.tag.toString(),
                    "A new notification created",R.drawable.baseline_person_add_24)
            }
    }

}
