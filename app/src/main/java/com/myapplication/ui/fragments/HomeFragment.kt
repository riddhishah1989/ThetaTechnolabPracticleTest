package com.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.myapplication.R
import com.myapplication.network.APICallBack
import com.myapplication.utils.CommonUtils
import com.myapplication.viewmodels.UserListViewModel
import com.myapplication.viewmodels.ViewModelProviderFactory


class HomeFragment : Fragment() {

    private lateinit var viewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val factory = ViewModelProviderFactory()
        viewModel = ViewModelProvider(this, factory)[UserListViewModel::class.java]
        loadData()
    }

    private fun loadData() {
        viewModel.getUserListData(requireActivity(), object : APICallBack {
            override fun onLoading() {
                //CommonUtils.showProgressDialog(requireActivity())
            }

            override fun onSuccess() {
                CommonUtils.printErrorLog("yes sucess")
            }

            override fun onError(exception: Exception) {
                CommonUtils.showToast(requireActivity(), exception.message!!)
            }

            override fun onNoInternetFound(message: String) {
                CommonUtils.showToast(requireActivity(), message)
            }

        })

    }

}