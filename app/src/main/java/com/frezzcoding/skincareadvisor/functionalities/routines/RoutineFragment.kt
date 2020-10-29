package com.frezzcoding.skincareadvisor.functionalities.routines

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.di.Injectable
import javax.inject.Inject

class RoutineFragment : Fragment(R.layout.routine_view), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

}