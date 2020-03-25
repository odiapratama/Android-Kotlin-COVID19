package com.covid19.monitoring.views.ui.statistics

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingFragment
import com.covid19.monitoring.databinding.StatisticsFragmentBinding
import com.covid19.monitoring.model.RegionData
import com.covid19.monitoring.services.Status
import com.covid19.monitoring.utils.REGION_DATA_EXTRA
import com.covid19.monitoring.views.adapters.RegionAdapter
import com.covid19.monitoring.views.ui.detail.DetailActivity
import com.covid19.monitoring.views.ui.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class StatisticsFragment : DataBindingFragment() {

    private val viewModel by sharedViewModel<HomeViewModel>()
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
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRegion.adapter = regionAdapter
        initListener()
        observeData()
    }

    private fun observeData() {
        viewModel.listRegionData.observe(viewLifecycleOwner, Observer { res ->
            when (res.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    regionAdapter.updateData(res.data)
                }
                Status.ERROR -> {
                    regionAdapter.updateData(res.data)
                }
            }
        })
    }

    private fun initListener() {
        regionAdapter.listener = object : RegionAdapter.OnItemClickListener {
            override fun onClick(view: View, regionData: RegionData) {
                val intent = Intent(this@StatisticsFragment.context, DetailActivity::class.java)
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    this@StatisticsFragment.context as Activity?,
                    view,
                    regionData.countryRegion
                )
                intent.putExtra(REGION_DATA_EXTRA, regionData)
                startActivity(intent, options.toBundle())
            }
        }
    }
}
