package com.timkhakimov.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.timkhakimov.quotes.data.model.Quote
import com.timkhakimov.quotes.data.model.QuoteInfo
import com.timkhakimov.quotes.presentation.QuotesViewModel
import com.timkhakimov.quotes.ui.adapter.QuotesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = QuotesAdapter()
    private lateinit var viewModel : QuotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpList()
        initViewModel()
        if(savedInstanceState == null) {
            viewModel.start()
        }
        observeData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
            QuotesViewModel::class.java
        )
    }

    private fun setUpList() {
        rvQuotes.layoutManager = LinearLayoutManager(this)
        rvQuotes.adapter = adapter
    }

    private fun observeData() {
        viewModel.observeQuotes(
            this,
            Observer { quotes ->
                setQuotes(quotes ?: return@Observer)
            }
        )
    }

    private fun setQuotes(quotes: List<QuoteInfo>) {
        adapter.setQuotes(quotes)
    }
}