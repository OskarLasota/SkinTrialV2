package com.frezzcoding.skincareadvisor.functionalities.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.di.Injectable
import javax.inject.Inject

class AccountFragment : Fragment(R.layout.account_view), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("account fragment view created")
    }

}