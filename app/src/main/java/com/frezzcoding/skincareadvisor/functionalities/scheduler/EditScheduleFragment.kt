package com.frezzcoding.skincareadvisor.functionalities.scheduler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.databinding.EditscheduleFragmentBinding

class EditScheduleFragment : Fragment() {

    private lateinit var binding : EditscheduleFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.editschedule_fragment, container, false
        )



        return binding.root
    }

}