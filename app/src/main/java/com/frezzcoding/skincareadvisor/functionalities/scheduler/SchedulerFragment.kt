package com.frezzcoding.skincareadvisor.functionalities.scheduler

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.di.Injectable
import javax.inject.Inject

class SchedulerFragment : Fragment(R.layout.scheduler_view), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

}