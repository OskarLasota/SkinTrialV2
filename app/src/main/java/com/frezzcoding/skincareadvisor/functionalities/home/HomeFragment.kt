package com.frezzcoding.skincareadvisor.functionalities.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.di.Injectable
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.home_view), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.here()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        cannot initialize lazy functions here
         */
    }

}