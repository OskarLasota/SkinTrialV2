package com.frezzcoding.skincareadvisor.functionalities.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.data.Curiosity
import com.frezzcoding.skincareadvisor.databinding.HomeViewBinding
import com.frezzcoding.skincareadvisor.functionalities.home.adapter.CuriosityAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), CuriosityAdapter.OnItemClickListener {


    private lateinit var adapter: CuriosityAdapter
    private lateinit var binding: HomeViewBinding
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    private fun registerObservers() {
        homeViewModel.curiosities.observe(viewLifecycleOwner, {
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


    private fun setupAdapter(list: ArrayList<Curiosity>) {
        adapter = CuriosityAdapter(requireContext(), list, true, this)
        binding.viewpager.adapter = adapter

        binding.indicator.highlighterViewDelegate = {
            val highlighter = View(requireActivity())
            highlighter.layoutParams = FrameLayout.LayoutParams(24, 24)
            highlighter.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.creamy))
            highlighter
        }

        binding.indicator.unselectedViewDelegate = {
            val unselected = View(requireActivity())
            unselected.layoutParams = LinearLayout.LayoutParams(24, 24)
            unselected.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.creamy))
            unselected.alpha = 0.4f
            unselected
        }
        binding.viewpager.onIndicatorProgress = { selectingPosition, progress ->
            binding.indicator.onPageScrolled(
                selectingPosition,
                progress
            )
        }

        binding.indicator.updateIndicatorCounts(binding.viewpager.indicatorCount)
    }

    override fun onItemClick(curiosity: Curiosity) {
        Toast.makeText(requireContext(), curiosity.description, Toast.LENGTH_LONG).show()
    }


}