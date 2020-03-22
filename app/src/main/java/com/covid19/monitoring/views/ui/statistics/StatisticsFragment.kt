package com.covid19.monitoring.views.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingFragment
import com.covid19.monitoring.databinding.StatisticsFragmentBinding
import com.covid19.monitoring.services.Status
import com.covid19.monitoring.views.adapters.RegionAdapter
import org.koin.android.viewmodel.ext.android.getViewModel


class StatisticsFragment : DataBindingFragment() {

    private lateinit var viewModel: StatisticsViewModel
    private lateinit var binding: StatisticsFragmentBinding
    private val regionAdapter by lazy { RegionAdapter(emptyList()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<StatisticsFragmentBinding>(
            inflater,
            R.layout.statistics_fragment,
            container
        ).apply {
            lifecycleOwner = this@StatisticsFragment
            viewModel = getViewModel<StatisticsViewModel>().apply { fetch() }
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listRegionData.observe(viewLifecycleOwner, Observer { res ->
            when (res.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    binding.rvRegion.adapter = regionAdapter
                    regionAdapter.updateData(res.data)
                }
                Status.ERROR -> {
                }
            }
        })
        /*viewModel.getRegionData().observe(viewLifecycleOwner, Observer { res ->
            when (res.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    binding.rvRegion.adapter = regionAdapter
                    regionAdapter.updateData(res.data)
                }
                Status.ERROR -> {
                }
            }
        })*/
    }
}
