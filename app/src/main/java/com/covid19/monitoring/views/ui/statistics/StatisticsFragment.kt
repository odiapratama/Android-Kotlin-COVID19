package com.covid19.monitoring.views.ui.statistics

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingFragment
import com.covid19.monitoring.databinding.StatisticsFragmentBinding


class StatisticsFragment : DataBindingFragment() {

    companion object {
        fun newInstance() =
            StatisticsFragment()
    }

    private lateinit var viewModel: StatisticsViewModel

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
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StatisticsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
