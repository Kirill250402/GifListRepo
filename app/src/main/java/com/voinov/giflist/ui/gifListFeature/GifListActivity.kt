package com.voinov.giflist.ui.gifListFeature

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voinov.giflist.R
import com.voinov.giflist.ui.recyclerUtility.GiphyListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class GifListActivity : AppCompatActivity() {

    private val viewModel: GifListViewModel by viewModel()
    private val giphyListAdapter = GiphyListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_list)

        recyclerInit()
        observing()
    }

    private fun observing() {
        lifecycleScope.launchWhenResumed {
            viewModel.gifList.collect { response ->
                giphyListAdapter.setList(response.data)
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.error.collect {
                Toast.makeText(
                    this@GifListActivity,
                    getString(R.string.request_failed_with_message, it),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        lifecycleScope.launchWhenResumed {
            viewModel.isLoading.collect {
                progressBar.isVisible = it
            }
        }
    }

    private fun recyclerInit() {
        val gridLayoutManager = GridLayoutManager(this, NUMBER_OF_COLUMNS)

        val gifRecyclerView = findViewById<RecyclerView>(R.id.giphyListRecycler)
        with(gifRecyclerView) {
            adapter = giphyListAdapter
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
        }

        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)

        ResourcesCompat
            .getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }

        gifRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    companion object {
        const val NUMBER_OF_COLUMNS = 2
    }
}