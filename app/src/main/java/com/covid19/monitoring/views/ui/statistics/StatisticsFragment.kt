package com.covid19.monitoring.views.ui.statistics

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingFragment
import com.covid19.monitoring.databinding.StatisticsFragmentBinding
import com.covid19.monitoring.services.Status
import com.covid19.monitoring.utils.REGION_DATA_EXTRA
import com.covid19.monitoring.views.adapters.RegionAdapter
import com.covid19.monitoring.views.ui.detail.DetailActivity
import com.covid19.monitoring.views.ui.home.HomeViewModel
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import org.koin.android.viewmodel.ext.android.sharedViewModel

class StatisticsFragment : DataBindingFragment() {

    private lateinit var regionAdapter: RegionAdapter
    private val viewModel by sharedViewModel<HomeViewModel>()
    private lateinit var binding: StatisticsFragmentBinding
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        regionAdapter = RegionAdapter {
            val intent = Intent(this@StatisticsFragment.context, DetailActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                requireActivity(),
                view,
                it.countryRegion
            )
            intent.putExtra(REGION_DATA_EXTRA, it)
            startActivity(intent, options.toBundle())
        }
        binding.rvRegion.adapter = regionAdapter
        skeleton = binding.rvRegion.applySkeleton(R.layout.item_daily_placeholder, 5).apply {
            maskCornerRadius = 24F
            maskColor = ContextCompat.getColor(requireContext(), R.color.thin_gray)
        }
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            listRegionData.observe(viewLifecycleOwner, {
                viewModel.listRegionData.observe(viewLifecycleOwner, { res ->
                    when (res.status) {
                        Status.LOADING -> skeleton.showSkeleton()
                        Status.SUCCESS -> skeleton.showOriginal()
                        Status.ERROR -> {
                            Toast.makeText(requireContext(), res.msg, Toast.LENGTH_SHORT).show()
                            skeleton.showOriginal()
                        }
                        Status.CACHED -> skeleton.showOriginal()
                    }
                })

            })

            regionDataSource.observe(viewLifecycleOwner, {
                if (!it.isNullOrEmpty()) regionAdapter.submitList(it)
            })
        }
    }
}
