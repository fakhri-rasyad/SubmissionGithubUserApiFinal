package com.d121211017.submissiongithubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d121211017.submissiongithubuser.model.ItemsItem
import com.d121211017.submissiongithubuser.model.SearchResponse
import com.d121211017.submissiongithubuser.repository.ApiConfig
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

    private val _isError = MutableLiveData<Boolean>()
    val isError : LiveData<Boolean> = _isError

    fun getUserList(userName : String) {
        _userList.value = null
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
                    _isError.value = true
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                _isError.value = true
                Log.e(TAG, "Error : ${t.message}")
            }


        })

    }


}