package com.covid19.monitoring.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.covid19.monitoring.R
import com.covid19.monitoring.data.model.DailyUpdateData
import com.covid19.monitoring.databinding.ItemDailyUpdatesBinding
import com.covid19.monitoring.extensions.setLeftDrawable

class DailyUpdatesAdapter :
    PagedListAdapter<DailyUpdateData, DailyUpdatesAdapter.BindingHolder>(DiffUtilCallback()) {

    class DiffUtilCallback : DiffUtil.ItemCallback<DailyUpdateData>() {
        override fun areItemsTheSame(oldItem: DailyUpdateData, newItem: DailyUpdateData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DailyUpdateData,
            newItem: DailyUpdateData
        ): Boolean {
            return oldItem.objectid == newItem.objectid
        }
    }

    inner class BindingHolder(
        var binding: ItemDailyUpdatesBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDailyUpdatesBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }


    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
        if (position == currentList?.size?.minus(1) ?: 0) {
            if (data?.deltaConfirmed == null || data.deltaConfirmed == 0) {
                holder.binding.tvConfirmed.setLeftDrawable(R.drawable.ic_right_arrow)
            } else {
                holder.binding.tvConfirmed.setLeftDrawable(R.drawable.ic_up_arrow)
            }
            if (data?.deltaRecovered == null || data.deltaRecovered == 0) {
                holder.binding.tvRecovered.setLeftDrawable(R.drawable.ic_right_arrow)
            } else {
                holder.binding.tvRecovered.setLeftDrawable(R.drawable.ic_up_arrow)
            }
        } else {
            val nextData = getItem(position + 1)
            when {
                data?.deltaConfirmed ?: 0 > nextData?.deltaConfirmed ?: 0 -> {
                    holder.binding.tvConfirmed.setLeftDrawable(R.drawable.ic_up_arrow)
                }
                data?.deltaConfirmed ?: 0 < nextData?.deltaConfirmed ?: 0 -> {
                    holder.binding.tvConfirmed.setLeftDrawable(R.drawable.ic_down_arrow)
                }
                else -> {
                    holder.binding.tvConfirmed.setLeftDrawable(R.drawable.ic_right_arrow)
                }
            }
            when {
                data?.deltaRecovered ?: 0 > nextData?.deltaRecovered ?: 0 -> {
                    holder.binding.tvRecovered.setLeftDrawable(R.drawable.ic_up_arrow)
                }
                data?.deltaRecovered ?: 0 < nextData?.deltaRecovered ?: 0 -> {
                    holder.binding.tvRecovered.setLeftDrawable(R.drawable.ic_down_arrow)
                }
                else -> {
                    holder.binding.tvRecovered.setLeftDrawable(R.drawable.ic_right_arrow)
                }
            }
        }
    }
}