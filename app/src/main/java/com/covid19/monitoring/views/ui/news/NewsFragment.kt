package com.covid19.monitoring.views.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingFragment
import com.covid19.monitoring.databinding.NewsFragmentBinding
import com.covid19.monitoring.services.Status
import com.covid19.monitoring.views.adapters.DailyUpdatesAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import org.koin.android.viewmodel.ext.android.getViewModel

class NewsFragment : DataBindingFragment() {

    private val dailyAdapter: DailyUpdatesAdapter by lazy { DailyUpdatesAdapter() }
    private lateinit var binding: NewsFragmentBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        skeleton = binding.rvDailyUpdates.applySkeleton(R.layout.item_daily_placeholder, 5).apply {
            maskCornerRadius = 24F
            maskColor = ContextCompat.getColor(requireContext(), R.color.thin_gray)
        }
        binding.rvDailyUpdates.apply {
            hasFixedSize()
            setItemViewCacheSize(20)
        }
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            dailyUpdatesData.observe(viewLifecycleOwner, { res ->
                when (res.status) {
                    Status.LOADING -> {
                        if (res.data.isNullOrEmpty()) skeleton.showSkeleton()
                        else dailyAdapter.submitList(res.data.reversed())
                    }
                    Status.SUCCESS -> {
                        dailyAdapter.submitList(res.data?.reversed())
                        skeleton.showOriginal()
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), res.msg, Toast.LENGTH_SHORT).show()
                        skeleton.showOriginal()
                    }
                    Status.CACHED -> {
                        dailyAdapter.submitList(res.data?.reversed())
                        skeleton.showOriginal()
                    }
                }
            })
        }
    }
}
