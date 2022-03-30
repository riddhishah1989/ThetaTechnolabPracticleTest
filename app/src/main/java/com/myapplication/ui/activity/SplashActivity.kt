package com.myapplication.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.myapplication.R
import com.myapplication.utils.SharedPrefsUtils

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent = if (SharedPrefsUtils.getBoolean(SharedPrefsUtils.IS_USER_LOGGED_IN)) {
                Intent(this, MainActivity::class.java)

            } else {
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 2000)

    }
}