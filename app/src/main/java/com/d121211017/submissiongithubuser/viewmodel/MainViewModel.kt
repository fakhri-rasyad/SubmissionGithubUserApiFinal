package com.d121211017.submissiongithubuser.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d121211017.submissiongithubuser.model.ItemsItem
import com.d121211017.submissiongithubuser.model.SearchResponse
import com.d121211017.submissiongithubuser.repository.ApiConfig
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    companion object{
        const val TAG : String = "MainViewModel"
    }

    private val _userList = MutableLiveData<List<ItemsItem?>?>()
    val userList : MutableLiveData<List<ItemsItem?>?> = _userList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getUserList(userName : String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUserSearch(userName)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    _userList.value = responseBody.items

                } else {
                    Log.e(TAG, "Error : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "Error : ${t.message}")
            }


        })

    }


}