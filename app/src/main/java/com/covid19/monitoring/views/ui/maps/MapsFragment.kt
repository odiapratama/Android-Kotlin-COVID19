package com.covid19.monitoring.views.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.covid19.monitoring.R
import com.covid19.monitoring.base.DataBindingFragment
import com.covid19.monitoring.databinding.MapsFragmentBinding
import com.covid19.monitoring.model.RegionData
import com.covid19.monitoring.services.Status
import com.covid19.monitoring.utils.slideAnimation
import com.covid19.monitoring.views.ui.home.HomeViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.viewmodel.ext.android.sharedViewModel


class MapsFragment : DataBindingFragment(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    private lateinit var binding: MapsFragmentBinding
    private val markers = mutableListOf<Marker>()
    private val viewModel by sharedViewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = binding<MapsFragmentBinding>(
            inflater,
            R.layout.maps_fragment,
            container
        ).apply {
            lifecycleOwner = this@MapsFragment
        }
        binding = view
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fMaps.onCreate(savedInstanceState)
        binding.fMaps.onResume()
        binding.fMaps.getMapAsync(this)
        observeData()
    }

    private fun observeData() {
        viewModel.listRegionData.observe(viewLifecycleOwner, { res ->
            when (res.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    initMarkers(res.data)
                }
                Status.ERROR -> {
                    initMarkers(res.data)
                }
            }
        })
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.let { googleMap = it }
        googleMap?.mapType = GoogleMap.MAP_TYPE_HYBRID
    }

    private fun initMarkers(listRegion: List<RegionData>?) {
        googleMap?.clear()
        markers.clear()
        listRegion?.forEach { data ->
            val marker = googleMap?.addMarker(
                MarkerOptions()
                    .position(LatLng(data.lat ?: 0.0, data.long ?: 0.0))
                    .title(data.location)
            )
            marker?.let { markers.add(it) }
        }
        listRegion?.let {
            initListener(listRegion)
        }
    }

    private fun initListener(listRegion: List<RegionData>?) {
        googleMap?.setOnMarkerClickListener {
            it.showInfoWindow()
            googleMap?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        it.position.latitude,
                        it.position.longitude
                    ), 6f
                )
            )
            listRegion?.firstOrNull { region ->
                region.lat == it.position.latitude && region.long == it.position.longitude
            }?.run {
                slideAnimation(binding.clConfirmed, true)
                binding.regionData = this
            }
            true
        }

        binding.ivClose.setOnClickListener {
            slideAnimation(binding.clConfirmed, false)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.fMaps.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.fMaps.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.fMaps.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.fMaps.onLowMemory()
    }
}
