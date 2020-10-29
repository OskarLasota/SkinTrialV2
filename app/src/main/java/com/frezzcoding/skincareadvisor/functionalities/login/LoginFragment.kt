package com.frezzcoding.skincareadvisor.functionalities.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.frezzcoding.skincareadvisor.R

class LoginFragment : Fragment(R.layout.login_view) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("on view created login fragment")
    }
}