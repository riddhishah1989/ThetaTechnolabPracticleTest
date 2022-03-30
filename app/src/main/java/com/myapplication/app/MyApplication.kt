package com.myapplication.app

import android.app.Application
import com.myapplication.utils.SharedPrefsUtils


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPrefsUtils.init(this)

    }

}