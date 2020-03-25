package com.covid19.monitoring.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.covid19.monitoring.databinding.ItemRegionBinding
import com.covid19.monitoring.model.RegionData

class RegionAdapter(private var listRegion: List<RegionData>) :
    RecyclerView.Adapter<RegionAdapter.BindingHolder>() {

    lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(view: View, regionData: RegionData)
    }

    inner class BindingHolder(val binding: ItemRegionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRegionBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }

    override fun getItemCount(): Int = listRegion.size

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val data = listRegion[position]
        holder.binding.data = data
        holder.binding.cvRegion.setOnClickListener {
            listener.onClick(it, data)
        }
    }

    fun updateData(newList: List<RegionData>?) {
        newList?.let {
            listRegion = it
            notifyDataSetChanged()
        }
    }
}