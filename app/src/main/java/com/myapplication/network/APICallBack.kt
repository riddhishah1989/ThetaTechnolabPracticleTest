package com.myapplication.network

import com.myapplication.data.models.UserModelItem

interface APICallBack {
    fun onLoading()
    fun onSuccess(list: ArrayList<UserModelItem>?)
    fun onError(exception: Exception)
    fun onNoInternetFound(message:String)
}