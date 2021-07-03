package com.frezzcoding.skincareadvisor.functionalities.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.frezzcoding.skincareadvisor.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.account_view) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("account fragment view created")
    }

}