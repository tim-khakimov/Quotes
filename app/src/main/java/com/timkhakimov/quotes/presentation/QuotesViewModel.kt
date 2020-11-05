package com.timkhakimov.quotes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.timkhakimov.quotes.data.model.QuoteInfo
import com.timkhakimov.quotes.data.repository.QuotesObserver
import com.timkhakimov.quotes.data.repository.QuotesRepository
import com.timkhakimov.quotes.data.socket.SocketWrapper

class QuotesViewModel : ViewModel(), QuotesObserver {

    private val quotesLiveData = MutableLiveData<List<QuoteInfo>>()
    private var quotesRepository = QuotesRepository(SocketWrapper())    //todo инициализировать

    fun start() {
        quotesRepository.subscribe(this)
    }

    private fun finish() {
        quotesRepository.unsubscribe()
    }

    override fun onQuotesUpdated(quotes: List<QuoteInfo>) {
        quotesLiveData.postValue(quotes)
    }

    fun observeQuotes(
        lifecycleOwner: LifecycleOwner,
        observer: Observer<List<QuoteInfo>>
    ) {
        quotesLiveData.observe(lifecycleOwner, observer)
    }

    override fun onCleared() {
        finish()
        super.onCleared()
    }
}