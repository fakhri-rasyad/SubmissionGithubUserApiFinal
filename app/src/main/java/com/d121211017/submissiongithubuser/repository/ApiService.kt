package com.d121211017.submissiongithubuser.repository

import com.d121211017.submissiongithubuser.model.FollowersResponse
import com.d121211017.submissiongithubuser.model.FollowingResponse
import com.d121211017.submissiongithubuser.model.SearchResponse
import com.d121211017.submissiongithubuser.model.UserResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val ACCESS_TOKEN = "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"
interface ApiService {


    @Headers("Authorization: token $ACCESS_TOKEN")
    @GET("/search/users")
    fun getUserSearch(
        @Query("q") userName : String
    ) : Call<SearchResponse>


    @Headers("Authorization: token $ACCESS_TOKEN")
    @GET("/users/{username}")
    fun getUser(
        @Path("username") userName: String
    ) : Call<UserResponse>


    @Headers("Authorization: token $ACCESS_TOKEN")
    @GET("/users/{username}/followers")
    fun getFollowers(
        @Path("username") userName: String
    ) : Call<FollowersResponse>


    @Headers("Authorization: token $ACCESS_TOKEN")
    @GET("/users/{username}/following")
    fun getFollowing(
        @Path("username") userName: String
    ) : Call<FollowingResponse>


}