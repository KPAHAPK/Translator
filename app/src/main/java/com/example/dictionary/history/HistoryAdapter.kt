package com.example.dictionary.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.ActivityHistoryRecyclerViewItemBinding
import com.example.dictionary.history.HistoryAdapter.RecyclerItemViewHolder
import com.example.dictionary.model.data.DataModel

class HistoryAdapter : RecyclerView.Adapter<RecyclerItemViewHolder>() {

    private lateinit var binding: ActivityHistoryRecyclerViewItemBinding
    private var data: List<DataModel> = arrayListOf()

    fun set(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerItemViewHolder(
            ActivityHistoryRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class RecyclerItemViewHolder(private val binding: ActivityHistoryRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerHistoryTextviewRecyclerItem.text = data.text
                itemView.setOnClickListener {
                    Toast.makeText(itemView.context,
                        "on click: ${data.text}",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}