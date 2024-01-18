package com.voinov.giflist.ui.mainActivity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.voinov.giflist.R
import com.voinov.giflist.ui.gifListFeature.GifListActivity
import com.voinov.giflist.ui.gifSearch.SearchGifActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gifListButton = findViewById<TextView>(R.id.openGifListButton)
        gifListButton.setOnClickListener {
            openActivity(GifListActivity::class.java)
        }

        val searchGifListButton = findViewById<TextView>(R.id.openSearchListButton)
        searchGifListButton.setOnClickListener {
            openActivity(SearchGifActivity::class.java)
        }
    }

    private fun openActivity(clasz: Class<*>) {
        val intent = Intent(this, clasz)
        startActivity(intent)
    }
}