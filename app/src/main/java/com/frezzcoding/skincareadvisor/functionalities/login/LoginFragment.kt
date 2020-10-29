package com.frezzcoding.skincareadvisor.functionalities.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.di.Injectable
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.login_view), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("on view created login fragment")
    }
}