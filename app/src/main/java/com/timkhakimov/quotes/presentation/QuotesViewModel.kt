package com.timkhakimov.quotes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.timkhakimov.quotes.data.model.Quote

class QuotesViewModel : ViewModel() {

    private val quotesLiveData = MutableLiveData<List<Quote>>()

    fun start() {
        //todo коннект к сокету
    }

    private fun finish() {
        //todo дисконнект от сокета
    }

    fun observeQuotes(
        lifecycleOwner: LifecycleOwner,
        observer: Observer<List<Quote>>
    ) {
        quotesLiveData.observe(lifecycleOwner, observer)
    }

    override fun onCleared() {
        finish()
        super.onCleared()
    }
}