package com.example.studentapps.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentapps.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(studentId: String) {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"

        val stringRequest = StringRequest( //semuanya pilih yang berbau volly pad di ketik
            Request.Method.GET, url, { // ini jika volly nya succses maka ini yang di tampilkan
//                loadingLD.value = false
                Log.d("show_volleyDetail", it.toString())

                val result = Gson().fromJson<Student>(it, Student::class.java)

                // ini variable penampung nya
                studentLD.value = result// ini bakalan di convert json string nya menjadi list of student

                //Log.d("show_volley", result.toString())
            },
            {// ini jika volly nya gagal
                Log.d("show_volleyDetail", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}
