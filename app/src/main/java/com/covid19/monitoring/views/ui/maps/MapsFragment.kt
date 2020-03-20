package com.covid19.monitoring.views.ui.maps

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingFragment
import com.covid19.monitoring.databinding.MapsFragmentBinding


class MapsFragment : DataBindingFragment() {

    companion object {
        fun newInstance() = MapsFragment()
    }

    private lateinit var viewModel: MapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<MapsFragmentBinding>(inflater, R.layout.maps_fragment, container).apply {
            lifecycleOwner = this@MapsFragment
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MapsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
