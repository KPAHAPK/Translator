package com.example.dictionary.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.R
import com.example.dictionary.convertMeaningsToString
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.isOnline
import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.data.DataModel
import com.example.dictionary.model.data.Meanings
import com.example.dictionary.model.viewmodel.MainViewModel
import com.example.dictionary.ui.base.BaseActivity
import com.example.dictionary.ui.main.adapter.MainAdapter
import dagger.android.AndroidInjection
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.View as androidView

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    override lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings[0].imageUrl
                    )
                )
            }

        }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener{
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    viewModel.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }

        }

    private val fabClickListener = androidView.OnClickListener {
        val searchDialogFragment = SearchDialogFragment.newInstance()
        searchDialogFragment.setOnSearchClickListener(object :
            SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            }
        })
        searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }

        val model: MainViewModel by viewModel()
        viewModel = model

        viewModel.subscribe().observe(this) {
            renderData(it)
        }
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.layoutManager =
            LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                val dataModel = appState.data
                if (dataModel.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_title_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    adapter.setData((dataModel))
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
                showViewWorking()
                showAlertDialog(
                    getString(R.string.error_stub),
                    appState.error.message.toString()
                )
            }
        }
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = androidView.GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = androidView.VISIBLE
    }


}