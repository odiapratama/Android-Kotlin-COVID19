package com.covid19.monitoring.views.ui.detail

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingActivity
import com.covid19.monitoring.databinding.ActivityDetailBinding
import com.covid19.monitoring.extensions.applyMaterialTransform
import com.covid19.monitoring.data.model.RegionData
import com.covid19.monitoring.utils.REGION_DATA_EXTRA
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : DataBindingActivity() {

    private val binding: ActivityDetailBinding by binding(R.layout.activity_detail)
    private val detailViewModel by viewModel<DetailViewModel>()
    private lateinit var regionData: RegionData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        regionData = intent.getSerializableExtra(REGION_DATA_EXTRA) as RegionData
        applyMaterialTransform(regionData.countryRegion ?: "")
        binding.data = regionData
        initView()
        initListener()
    }

    private fun initView() {
        val pieDataSet = PieDataSet(
            listOf(
                PieEntry(regionData.confirmed?.toFloat() ?: 0f, getString(R.string.confirmed)),
                PieEntry(regionData.recovered?.toFloat() ?: 0f, getString(R.string.recovered)),
                PieEntry(regionData.deaths?.toFloat() ?: 0f, getString(R.string.deaths))
            ), getString(R.string.app_name)
        )
        val colors = listOf(
            ContextCompat.getColor(this, R.color.yellow),
            ContextCompat.getColor(this, R.color.green),
            ContextCompat.getColor(this, R.color.orange)
        )
        pieDataSet.colors = colors
        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(false)
        with(binding.pieChart) {
            data = pieData
            legend.isEnabled = false
            description = null
            holeRadius = 75f
            setHoleColor(ContextCompat.getColor(this@DetailActivity, R.color.light_gray))
            setDrawEntryLabels(false)
            animateY(1000, Easing.EaseInOutQuart)
            invalidate()
        }
    }

    private fun initListener() {
        binding.fabBack.setOnClickListener {
            onBackPressed()
        }
    }
}
