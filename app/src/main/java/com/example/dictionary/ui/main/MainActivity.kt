package com.example.dictionary.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.data.DataModel
import com.example.dictionary.model.viewmodel.MainViewModel
import com.example.dictionary.ui.base.BaseActivity
import com.example.dictionary.ui.main.adapter.MainAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject
import android.view.View as androidView

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    private var adapter: MainAdapter? = null
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModelFactory.create(MainViewModel::class.java)
        viewModel.subscribe().observe(this) {
            renderData(it)
        }

        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    viewModel.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter!!.setData((dataModel))
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = androidView.VISIBLE
                    binding.progressBarRound.visibility = androidView.GONE
                    binding.progressBarHorizontal.visibility = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = androidView.GONE
                    binding.progressBarRound.visibility = androidView.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = androidView.VISIBLE
        binding.loadingFrameLayout.visibility = androidView.GONE
        binding.errorLinearLayout.visibility = androidView.GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = androidView.GONE
        binding.loadingFrameLayout.visibility = androidView.VISIBLE
        binding.errorLinearLayout.visibility = androidView.GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = androidView.GONE
        binding.loadingFrameLayout.visibility = androidView.GONE
        binding.errorLinearLayout.visibility = androidView.VISIBLE
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            viewModel.getData("hi", true)
        }
    }


}