package com.voinov.giflist.ui.gifSearch

import android.os.Bundle
import android.view.Menu
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voinov.giflist.R
import com.voinov.giflist.ui.gifListFeature.GifListActivity
import com.voinov.giflist.ui.recyclerUtility.GiphyListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchGifActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModel()
    private val giphyListAdapter = GiphyListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_gif)

        initRecycler()
        initObserving()
    }

    private fun initObserving() {
        lifecycleScope.launchWhenResumed {
            viewModel.gifList.collect { response ->
                giphyListAdapter.setList(response.data)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.error.collect {
                Toast.makeText(
                    this@SearchGifActivity,
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

    private fun initRecycler() {
        val gridLayoutManager = GridLayoutManager(this, GifListActivity.NUMBER_OF_COLUMNS)

        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)

        ResourcesCompat
            .getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }

        val gifRecycler = findViewById<RecyclerView>(R.id.gifRecycler)
        with(gifRecycler) {
            layoutManager = gridLayoutManager
            adapter = giphyListAdapter
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = FIND_GIF_HINT

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    lifecycleScope.launchWhenResumed {
                        viewModel.searchGifByQuery(query)
                    }
                }
                return query != null
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    lifecycleScope.launchWhenResumed {
                        viewModel.searchGifByQuery(newText)
                    }
                }
                return newText != null
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        private const val FIND_GIF_HINT = "Find Gif!"
    }
}