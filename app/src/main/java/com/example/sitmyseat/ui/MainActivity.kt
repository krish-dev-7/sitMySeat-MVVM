package com.example.sitmyseat.ui

import MoviesRepository
import MoviesViewModel
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sitmyseat.R
import com.example.sitmyseat.adapters.MoviesAdapter
import com.example.sitmyseat.utils.Resource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MoviesViewModel
    lateinit var moviesAdapter : MoviesAdapter
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = MoviesRepository()
        val provider = MoviesViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, provider).get(MoviesViewModel::class.java)

        setRecyclerView(this)

        viewModel.moviesPage.observe(this, { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        moviesAdapter.differ.submitList(newsResponse.items)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })


    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    fun setRecyclerView(context: Context){
        moviesAdapter = MoviesAdapter()
        rvMovies.apply {
            adapter=moviesAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        }
    }
}