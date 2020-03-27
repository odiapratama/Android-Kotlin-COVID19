package com.covid19.monitoring.views.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingFragment
import com.covid19.monitoring.databinding.NewsFragmentBinding
import com.covid19.monitoring.services.Status
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
            getViewModel<NewsViewModel>().apply {
                vm = this
                viewModel = this
                fetchData()
            }
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
            dailyUpdatesData.observe(viewLifecycleOwner, Observer { res ->
                if (res.status == Status.SUCCESS) dailyAdapter.updateData(res.data?.reversed())
            })
        }
    }
}
