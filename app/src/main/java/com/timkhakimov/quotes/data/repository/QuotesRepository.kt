package com.timkhakimov.quotes.data.repository

import com.timkhakimov.quotes.data.model.QuotesData

class QuotesRepository(
    private val quotesDataObservable: QuotesDataObservable
) : QuotesDataObserver {

    private val quotesStorage = QuotesStorage()
    private var quotesObserver: QuotesObserver? = null

    fun subscribe(quotesObserver: QuotesObserver) {
        this.quotesObserver = quotesObserver
        quotesDataObservable.observeQuotesData(this)
    }

    fun unsubscribe() {
        quotesDataObservable.stop()
    }

    override fun onGetQuotes(quotesData: QuotesData) {
        quotesData.quotes?.let {
            quotesStorage.setData(it)
            notifyObserver()
        }
    }

    private fun notifyObserver() {
        quotesObserver?.onQuotesUpdated(quotesStorage.getData())
    }
}
