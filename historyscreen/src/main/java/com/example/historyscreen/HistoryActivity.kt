package com.example.historyscreen

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.core.BaseActivity
import com.example.historyscreen.databinding.ActivityHistoryBinding
import com.example.model.AppState
import com.example.model.DataModel
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var viewModel: HistoryViewModel
    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }
    val model: HistoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = model
        viewModel.subscribe().observe(this, {
            renderData(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.set(data)
    }
}