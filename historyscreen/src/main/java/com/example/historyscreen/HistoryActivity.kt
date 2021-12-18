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
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val model: HistoryViewModel by viewModel()
        viewModel = model
        viewModel.subscribe().observe(this, Observer {
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