package com.covid19.monitoring.views.ui.home

import android.os.Bundle
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingActivity
import com.covid19.monitoring.databinding.ActivityHomeBinding
import com.covid19.monitoring.extensions.applyExitMaterialTransform
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : DataBindingActivity() {

    private val binding: ActivityHomeBinding by binding(R.layout.activity_home)
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyExitMaterialTransform()
        binding.apply {
            lifecycleOwner = this@HomeActivity
            pagerAdapter = HomePagerAdapter(supportFragmentManager)
            navigation = bnvHome
            viewModel = homeViewModel
        }
        homeViewModel.fetch()
    }
}
