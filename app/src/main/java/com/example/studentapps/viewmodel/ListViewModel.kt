package com.example.studentapps.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentapps.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh() {
//        studentsLD.value = arrayListOf(
//            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100" +
//                    ".jpg/cc0000/ffffff"),
//            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
//                    ".jpg/5fa2dd/ffffff"),
//            Student("11204","Dinny","1994/10/07","6827808747",
//                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
//        ) langsung ambil dari volly nya

        studentLoadErrorLD.value = false
        loadingLD.value = true

        queue =Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest( //semuanya pilih yang berbau volly pad di ketik
            Request.Method.GET, url,
            {// ini jika volly nya succses maka ini yang di tampilkan
//                loadingLD.value = false
                Log.d("show_volley", it)

                val sType = object : TypeToken<List<Student>>() { }.type // objeck untuk gson nyya
                val result = Gson().fromJson<List<Student>>(it, sType) // ini variable penampung nya
                studentsLD.value = result as ArrayList<Student>?// ini bakalan di convert json string nya menjadi list of student
                loadingLD.value = false

                //Log.d("show_volley", result.toString())
            },
            {// ini jika volly nya gagal
                Log.d("show_volley", it.toString())
                studentLoadErrorLD.value = false
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}
