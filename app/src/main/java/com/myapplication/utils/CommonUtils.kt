package com.myapplication.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.util.Patterns
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.myapplication.R
import com.myapplication.data.custominterface.CustomDialogInterface
import pokercc.android.expandablerecyclerview.BuildConfig
import java.util.regex.Pattern


object CommonUtils {

    fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun getLocalUser(): Pair<String, String> {
        return Pair("riddhi@gmail.com", "Riddhi@123")
    }

    fun showToast(activity: Activity, message: String) {
        activity.runOnUiThread {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

    }

    fun printErrorLog(string: String) {
        showLog("Main", "" + string)
    }

    private fun showLog(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                return when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        true
                    }
                    else -> capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                }
            }
        } else {
            try {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

    private var progressDialog: Dialog? = null
    fun showProgressDialog(activity: Activity?) {
        try {
            progressDialog = Dialog(activity!!)
            progressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            progressDialog?.setContentView(R.layout.progress_dialog)
            progressDialog?.setCancelable(false)
            progressDialog?.setCanceledOnTouchOutside(false)
            if (progressDialog?.window != null) {
                val lp: WindowManager.LayoutParams = progressDialog?.window?.attributes!!
                lp.dimAmount = 0.6f
                progressDialog?.window?.attributes = lp
            }
            if (!activity.isFinishing) {
                progressDialog?.show()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun showAlert(
        context: Context?,
        title: String,
        message: String,
        callBack: CustomDialogInterface,
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            callBack.onPositiveBtnClicked()
            dialogInterface.dismiss()

        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            callBack.onNegativeBtnClicked()
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}