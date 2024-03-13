package com.d121211017.submissiongithubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d121211017.submissiongithubuser.model.FollowResponseItem
import com.d121211017.submissiongithubuser.model.UserResponse
import com.d121211017.submissiongithubuser.repository.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    companion object{
        private const val TAG = "Detail Activity"
    }

    private val _followingList = MutableLiveData<List<FollowResponseItem?>?>()
    val followingList : LiveData<List<FollowResponseItem?>?> = _followingList

    private val _followerList = MutableLiveData<List<FollowResponseItem?>?>()
    val followerList : LiveData<List<FollowResponseItem?>?> = _followerList

    private val _userData = MutableLiveData<UserResponse?>()
    val userData : LiveData<UserResponse?> = _userData

    private val _isLoadingUserData = MutableLiveData<Boolean>()
    val isLoadingUserData : LiveData<Boolean> = _isLoadingUserData

    private val _isLoadingUserFollowing = MutableLiveData<Boolean>()
    val isLoadingUserFollowing : LiveData<Boolean> = _isLoadingUserFollowing

    private val _isLoadingUserFollower = MutableLiveData<Boolean>()
    val isLoadingUserFollower : LiveData<Boolean> = _isLoadingUserFollower

    private val _isError = MutableLiveData<Boolean>()
    val isError : LiveData<Boolean> = _isError

    fun getUserData(username : String){
        _isLoadingUserData.value = true

        val client = ApiConfig.getApiService().getUser(username)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoadingUserData.value = false
                val responseBody = response.body()

                if(response.isSuccessful && responseBody != null){
                    _userData.value = responseBody
                } else {
                    Log.e(TAG, "error : ${response.message()}")
                    _isError.value = true
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, "error : ${t.message}")
                _isError.value = true
            }
        })
    }

    fun getFollowList(username: String){
        _isLoadingUserFollower.value = true
        _isLoadingUserFollowing.value = true
        var client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<FollowResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowResponseItem>>,
                response: Response<List<FollowResponseItem>>
            ) {
                _isLoadingUserFollower.value = false
                val responseBody = response.body()

                if(response.isSuccessful && responseBody != null){
                    _followerList.value = responseBody
                }else {
                    Log.e(TAG, "error : ${response.message()}")
                    _isError.value = true
                }
            }
            override fun onFailure(call: Call<List<FollowResponseItem>>, t: Throwable) {
                Log.e(TAG, "error : ${t.message}")
                _isError.value = true
            }
        })

        client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<FollowResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowResponseItem>>,
                response: Response<List<FollowResponseItem>>
            ) {
                _isLoadingUserFollowing.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null){
                    _followingList.value = responseBody
                }else {
                    Log.e(TAG, "error : ${response.message()}")
                    _isError.value = true
                }
            }
            override fun onFailure(call: Call<List<FollowResponseItem>>, t: Throwable) {
                Log.e(TAG, "error : ${t.message}")
                _isError.value = true
            }
        })
    }

}