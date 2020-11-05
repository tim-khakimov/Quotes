package com.timkhakimov.quotes.data.repository

interface QuotesDataObservable {
    fun observeQuotesData(quotesDataObserver: QuotesDataObserver)
    fun stop()
}