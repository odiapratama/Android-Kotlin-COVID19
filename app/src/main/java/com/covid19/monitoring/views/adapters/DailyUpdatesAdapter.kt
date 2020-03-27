package com.covid19.monitoring.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.covid19.monitoring.R
import com.covid19.monitoring.databinding.ItemDailyUpdatesBinding
import com.covid19.monitoring.extensions.setLeftDrawable
import com.covid19.monitoring.model.DailyUpdateData

class DailyUpdatesAdapter(private var listDailyData: List<DailyUpdateData>) :
    RecyclerView.Adapter<DailyUpdatesAdapter.BindingHolder>() {

    inner class BindingHolder(var binding: ItemDailyUpdatesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDailyUpdatesBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }

    override fun getItemCount(): Int = listDailyData.size

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val data = listDailyData[position]
        holder.binding.data = data
        if (position == listDailyData.size - 1) {
            if (data.deltaConfirmed == null || data.deltaConfirmed == 0) {
                holder.binding.tvConfirmed.setLeftDrawable(R.drawable.ic_right_arrow)
            } else {
                holder.binding.tvConfirmed.setLeftDrawable(R.drawable.ic_up_arrow)
            }
            if (data.deltaRecovered == null || data.deltaRecovered == 0) {
                holder.binding.tvRecovered.setLeftDrawable(R.drawable.ic_right_arrow)
            } else {
                holder.binding.tvRecovered.setLeftDrawable(R.drawable.ic_up_arrow)
            }
        } else {
            when {
                data.deltaConfirmed ?: 0 > listDailyData[position + 1].deltaConfirmed ?: 0 -> {
                    holder.binding.tvConfirmed.setLeftDrawable(R.drawable.ic_up_arrow)
                }
                data.deltaConfirmed ?: 0 < listDailyData[position + 1].deltaConfirmed ?: 0 -> {
                    holder.binding.tvConfirmed.setLeftDrawable(R.drawable.ic_down_arrow)
                }
                else -> {
                    holder.binding.tvConfirmed.setLeftDrawable(R.drawable.ic_right_arrow)
                }
            }
            when {
                data.deltaRecovered ?: 0 > listDailyData[position + 1].deltaRecovered ?: 0 -> {
                    holder.binding.tvRecovered.setLeftDrawable(R.drawable.ic_up_arrow)
                }
                data.deltaRecovered ?: 0 < listDailyData[position + 1].deltaRecovered ?: 0 -> {
                    holder.binding.tvRecovered.setLeftDrawable(R.drawable.ic_down_arrow)
                }
                else -> {
                    holder.binding.tvRecovered.setLeftDrawable(R.drawable.ic_right_arrow)
                }
            }
        }
    }

    fun updateData(newData: List<DailyUpdateData>?) {
        newData?.let {
            listDailyData = newData
            notifyDataSetChanged()
        }
    }
}