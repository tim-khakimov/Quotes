package com.timkhakimov.quotes.data.repository

import com.timkhakimov.quotes.data.model.QuotesData

interface QuotesDataObserver {
    fun onGetQuotes(quotesData: QuotesData)
}