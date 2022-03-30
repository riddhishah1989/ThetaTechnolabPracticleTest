package com.myapplication.viewmodels

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.myapplication.R
import com.myapplication.data.models.UserModelItem
import com.myapplication.network.APICallBack
import com.myapplication.network.RetrofitInstance
import com.myapplication.utils.CommonUtils
import kotlinx.coroutines.*

class UserListViewModel() : ViewModel() {

    private var job: Job? = null
    private var api = RetrofitInstance.retrofitAPI

    fun getUserListData(context: Activity, apiCallBack: APICallBack) {
        if (!CommonUtils.isNetworkAvailable(context)) {
            apiCallBack.onNoInternetFound(context.getString(R.string.no_network))
            return
        }

        apiCallBack.onLoading()

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = api.getUserListData(RetrofitInstance.BASE_URL)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.e("data", "api called")
                    if (response.body() == null) {
                        apiCallBack.onError(Exception("Response is null"))
                        return@withContext
                    }
                    var data = response.body()

                    if (data != null) {
                        for (i in data.indices) {
                            val model = data[i]
                            model.name = isCharContains(model.name)
                            if (model.child.isNotEmpty()) {//level 1
                                for (j in model.child.indices) {
                                    var jModel = model.child[j]
                                    jModel.name = isCharContains(jModel.name)
                                    if (jModel.child.isNotEmpty()){//level 2
                                        for (k in jModel.child.indices){
                                            var kModel = jModel.child[k]
                                            kModel.name = isCharContains(kModel.name)
                                        }
                                    }
                                }
                            }
                        }
                        apiCallBack.onSuccess(data as ArrayList<UserModelItem>)
                    }
                    var x = 0
                    //

                } else {
                    apiCallBack.onError(Exception(response.message()))
                }
            }
        }

    }

    private fun isCharContains(name: String): String {
        return if (name[0].equals("@")) {
            name.replace("@", "")
        } else
            name
    }
}