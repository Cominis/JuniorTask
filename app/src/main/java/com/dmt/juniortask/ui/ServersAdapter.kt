package com.dmt.juniortask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmt.juniortask.databinding.TextListItemBinding
import com.dmt.juniortask.domain.Server

class ServersAdapter (private val clickListener: ServerListener) : ListAdapter<Server, ServersAdapter.ViewHolder>(ServerDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: TextListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Server,
            clickListener: ServerListener
        ) {
            binding.clickListener = clickListener
            binding.server = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TextListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}

class ServerDiffCallback : DiffUtil.ItemCallback<Server>() {
    override fun areItemsTheSame(oldItem: Server, newItem: Server): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Server, newItem: Server): Boolean {
        return oldItem == newItem
    }

}

class ServerListener (val clickListener: (id : Long)-> Unit) {
    fun onClick(serverId: Long) = clickListener(serverId)
}