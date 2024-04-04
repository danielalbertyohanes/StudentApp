package com.example.studentapps.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapps.R
import com.example.studentapps.databinding.StudentListItemBinding
import com.example.studentapps.model.Student
import com.example.studentapps.util.loadImage


class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(var binding: StudentListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding= StudentListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return studentList.size
    }


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {

        holder.binding.txtID.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name
        holder.binding.btnDetail.setOnClickListener {

            val sharedPreferences = it.context.getSharedPreferences("student_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("studentId", studentList[position].id).apply()

            val action = StudentListFragmentDirections.actionStudentDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.itemView.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.itemView.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(studentList[position].photoUrl, progressBar)
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }


}