package com.frezzcoding.skincareadvisor.functionalities.scheduler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.utils.AppConstants.bundleSchedule
import com.frezzcoding.skincareadvisor.data.Schedule
import com.frezzcoding.skincareadvisor.databinding.SchedulerViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchedulerFragment : Fragment(), SchedulesAdapter.OnItemClickListener {

    private lateinit var binding: SchedulerViewBinding
    private lateinit var scheduleAdapter: SchedulesAdapter
    private val viewModel by viewModels<ScheduleCachingViewModel>()

    private lateinit var currentSchedules: ArrayList<Schedule>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.scheduler_view, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        viewModel.getSchedules()
    }

    private fun setListeners() {
        viewModel.schedules.observe(viewLifecycleOwner) { list ->
            currentSchedules = list as ArrayList
            if (!list.isNullOrEmpty()) {
                //update adapter
                scheduleAdapter = SchedulesAdapter(list, viewModel, this, requireContext())
                binding.scheduleRecycler.apply {
                    adapter = scheduleAdapter
                    layoutManager = GridLayoutManager(requireContext(), 1)
                }
            }
        }

        binding.fabAddschedule.setOnClickListener {
            val bundle = bundleOf(bundleSchedule to Schedule())
            Navigation.findNavController(binding.root).navigate(R.id.editScheduleFragment, bundle)
        }

        binding.scheduleRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !binding.fabAddschedule.isShown) binding.fabAddschedule.show() else if (dy > 0 && binding.fabAddschedule.isShown) binding.fabAddschedule.hide()
            }
        })

        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeSchedule(currentSchedules[viewHolder.adapterPosition])
                currentSchedules.removeAt(viewHolder.adapterPosition)
                scheduleAdapter.notifyDataSetChanged()
            }

        })
        helper.attachToRecyclerView(binding.scheduleRecycler)

    }

    override fun onItemClick(schedule: Schedule) {
        val bundle = bundleOf(bundleSchedule to schedule)
        Navigation.findNavController(binding.root).navigate(R.id.editScheduleFragment, bundle)
    }

}