package com.timkhakimov.quotes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.timkhakimov.quotes.data.model.Quote
import com.timkhakimov.quotes.data.repository.QuotesObserver
import com.timkhakimov.quotes.data.repository.QuotesRepository

class QuotesViewModel : ViewModel(), QuotesObserver {

    private val quotesLiveData = MutableLiveData<List<Quote>>()
    private lateinit var quotesRepository: QuotesRepository     //todo инициализировать

    fun start() {
        quotesRepository.subscribe(this)
    }

    private fun finish() {
        quotesRepository.unsubscribe()
    }

    override fun onQuotesUpdated(quotes: List<Quote>) {
        quotesLiveData.value = quotes
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