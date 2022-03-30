package com.myapplication.network

import com.myapplication.data.models.UserModelItem
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface API {

    @GET
    suspend fun getUserListData(@Url url: String): Response<List<UserModelItem>>
}