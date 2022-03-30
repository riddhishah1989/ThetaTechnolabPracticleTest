package com.myapplication.ui.activity.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myapplication.R
import com.myapplication.data.custominterface.CustomDialogInterface
import com.myapplication.ui.activity.LoginActivity
import com.myapplication.utils.CommonUtils
import com.myapplication.utils.SharedPrefsUtils
import kotlinx.android.synthetic.main.fragment_profile.*


class UserProfileFragment : Fragment(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        tvUserEmail.text = SharedPrefsUtils.getString(SharedPrefsUtils.USER_EMAIL_ID)

        btnLogout.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnLogout -> {
                CommonUtils.showAlert(requireContext(),"Logout","Are you sure you want to logout?",object : CustomDialogInterface{
                    override fun onPositiveBtnClicked() {
                        logout()
                    }

                    override fun onNegativeBtnClicked() {

                    }

                })

            }
        }
    }

    private fun logout() {
        SharedPrefsUtils.clearData()
        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // To clean up all activities
        startActivity(intent)
        activity?.finish()
    }

}