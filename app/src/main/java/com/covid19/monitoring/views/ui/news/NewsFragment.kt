package com.covid19.monitoring.views.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingFragment
import com.covid19.monitoring.databinding.NewsFragmentBinding
import com.covid19.monitoring.model.GlobalData
import com.covid19.monitoring.services.Status
import com.covid19.monitoring.utils.toNumberSeparator
import com.covid19.monitoring.views.adapters.DailyUpdatesAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class NewsFragment : DataBindingFragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: NewsFragmentBinding
    private val dailyAdapter: DailyUpdatesAdapter by lazy { DailyUpdatesAdapter(emptyList()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<NewsFragmentBinding>(inflater, R.layout.news_fragment, container).apply {
            lifecycleOwner = this@NewsFragment
            viewModel = getViewModel<NewsViewModel>().apply { fetchData() }
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDailyUpdates.adapter = dailyAdapter
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            globalData.observe(viewLifecycleOwner, Observer { res ->
                when (res.status) {
                    Status.LOADING -> {
                        loadingGlobalData(true)
                    }
                    Status.SUCCESS -> {
                        initViewGlobalData(res.data)
                        loadingGlobalData(false)
                    }
                    Status.ERROR -> {
                        initViewGlobalData(res.data)
                        loadingGlobalData(false)
                    }
                }
            })

            dailyUpdatesData.observe(viewLifecycleOwner, Observer { res ->
                when (res.status) {
                    Status.LOADING -> {
                        loadingDailyUpdates(true)
                    }
                    Status.SUCCESS -> {
                        dailyAdapter.updateData(res.data?.reversed())
                        loadingDailyUpdates(false)
                    }
                    Status.ERROR -> {
                        dailyAdapter.updateData(res.data?.reversed())
                        loadingDailyUpdates(false)
                    }
                }
            })
        }
    }

    private fun initViewGlobalData(globalData: GlobalData?) {
        with(binding) {
            tvConfirmed.text = globalData?.confirmed?.value?.toNumberSeparator()
            tvRecovered.text = globalData?.recovered?.value?.toNumberSeparator()
            tvDeaths.text = globalData?.deaths?.value?.toNumberSeparator()
        }
    }

    private fun loadingGlobalData(show: Boolean) {
        with(binding) {
            if (show) {
                shimmerGlobalData.visibility = View.VISIBLE
                shimmerGlobalData.isShimmerVisible
            } else {
                shimmerGlobalData.stopShimmer()
                shimmerGlobalData.visibility = View.GONE
            }
        }
    }

    private fun loadingDailyUpdates(show: Boolean) {
        with(binding) {
            if (show) {
                shimmerDailyUpdates.visibility = View.VISIBLE
                shimmerDailyUpdates.startShimmer()
            } else {
                shimmerDailyUpdates.stopShimmer()
                shimmerDailyUpdates.visibility = View.GONE
            }
        }
    }
}
