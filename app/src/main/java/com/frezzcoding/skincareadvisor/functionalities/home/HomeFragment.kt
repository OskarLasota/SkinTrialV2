package com.frezzcoding.skincareadvisor.functionalities.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.data.Curiosity
import com.frezzcoding.skincareadvisor.databinding.HomeViewBinding
import com.frezzcoding.skincareadvisor.di.Injectable
import com.frezzcoding.skincareadvisor.functionalities.home.adapter.CuriosityAdapter
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.home_view), Injectable, CuriosityAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var adapter : CuriosityAdapter
    private lateinit var binding : HomeViewBinding

    private val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.home_view, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getCuriosities()

        registerObservers()
    }

    private fun registerObservers(){
        homeViewModel.curiosities.observe(viewLifecycleOwner, Observer {
           it?.let {
               if (it.isNotEmpty()) {
                   setupAdapter(it)
               }
           }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        cannot initialize lazy functions here
         */
    }



    private fun setupAdapter(temp : ArrayList<Curiosity>){
        adapter = CuriosityAdapter(requireContext(), temp, true, this)
        binding.viewpager.adapter = adapter

        binding.indicator.highlighterViewDelegate = {
            val highlighter = View(requireActivity())
            highlighter.layoutParams = FrameLayout.LayoutParams(24, 24)
            highlighter.setBackgroundColor(requireContext().resources.getColor(R.color.creamy))
            highlighter
        }

        binding.indicator.unselectedViewDelegate = {
            val unselected = View(requireActivity())
            unselected.layoutParams = LinearLayout.LayoutParams(24, 24)
            unselected.setBackgroundColor(requireContext().resources.getColor(R.color.creamy))
            unselected.alpha = 0.4f
            unselected
        }
        binding.viewpager.onIndicatorProgress = { selectingPosition, progress -> binding.indicator.onPageScrolled(selectingPosition, progress) }

        binding.indicator.updateIndicatorCounts(binding.viewpager.indicatorCount)
    }

    override fun onItemClick(curiosity: Curiosity) {
        Toast.makeText(requireContext(), curiosity.description, Toast.LENGTH_LONG)
    }


}