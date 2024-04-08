package com.example.studentapps.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


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

//            val sharedPreferences = it.context.getSharedPreferences("student_prefs", Context.MODE_PRIVATE)
//            sharedPreferences.edit().putString("studentId", studentList[position].id).apply()

            val action = StudentListFragmentDirections.actionStudentDetailFragment(studentList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }

        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imageView, object :Callback{
            override fun onSuccess() {
                holder.binding.progressBar.visibility = View.INVISIBLE
                holder.binding.imageView.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
                Log.e("Picasso_error", "ERROR")
            }

        })


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