package com.covid19.monitoring.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.covid19.monitoring.data.model.RegionData
import com.covid19.monitoring.databinding.ItemRegionBinding

class RegionAdapter(
    private val onClicked: (RegionData) -> Unit
) : PagedListAdapter<RegionData, RegionAdapter.BindingHolder>(DiffCallback()) {

    inner class BindingHolder(val binding: ItemRegionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRegionBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
        holder.binding.cvRegion.setOnClickListener {
            if (data != null) {
                onClicked(data)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<RegionData>() {

        override fun areItemsTheSame(oldItem: RegionData, newItem: RegionData): Boolean {
            return oldItem.regionDataId == newItem.regionDataId
        }

        override fun areContentsTheSame(oldItem: RegionData, newItem: RegionData): Boolean {
            return oldItem == newItem
        }
    }
}