package com.example.dictionary.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.ActivityMainRecyclerviewItemBinding
import com.example.dictionary.model.data.DataModel

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    lateinit var itemBinding: ActivityMainRecyclerviewItemBinding

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        itemBinding = ActivityMainRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerItemViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemBinding.headerTextviewRecyclerItem.text = data.text
                itemBinding.descriptionTextviewRecyclerItem.text =
                    data.meanings?.get(0)?.translation?.text
                itemView.setOnClickListener {
                    openInNewWindow(data)
                }
            }
        }
    }

    fun openInNewWindow(data: DataModel) {
        onListItemClickListener.onItemClick(data)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }


}