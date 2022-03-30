package com.myapplication.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.myapplication.R
import com.myapplication.utils.CommonUtils
import com.myapplication.utils.SharedPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        btnLogin.setOnClickListener(this)
        edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 == null || p0.isEmpty()) {
                    textInputEmail.isErrorEnabled = true
                    textInputPwd.isErrorEnabled = true
                    return
                }
                textInputEmail.isErrorEnabled = false
                textInputPwd.isErrorEnabled = false

            }

        })

        edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 == null || p0.isEmpty()) {
                    textInputEmail.isErrorEnabled = true
                    textInputPwd.isErrorEnabled = true
                    return
                }
                textInputEmail.isErrorEnabled = false
                textInputPwd.isErrorEnabled = false
            }

        })
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.btnLogin) {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val user = CommonUtils.getLocalUser()
            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                textInputEmail.error = "Please enter email"
                textInputPwd.error = "Please enter password"
                return
            } else if (TextUtils.isEmpty(email)) {
                textInputEmail.error = "Please enter email"
                return
            } else if (!CommonUtils.isValidEmail(email)) {
                textInputEmail.error = "Please enter a valid email"
                return
            } else if (TextUtils.isEmpty(password)) {
                textInputPwd.error = "Please enter password"
                return
            } else if (email != user.first || password != user.second) {
                textInputPwd.isErrorEnabled = true
                textInputEmail.isErrorEnabled = true
                CommonUtils.showToast(this, "Please enter a valid login credentials")
                return
            } else {
                SharedPrefsUtils.setBoolean(SharedPrefsUtils.IS_USER_LOGGED_IN, true)
                SharedPrefsUtils.setString(SharedPrefsUtils.USER_EMAIL_ID, email)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

        }
    }
}