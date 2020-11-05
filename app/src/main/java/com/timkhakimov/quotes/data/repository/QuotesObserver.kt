package com.timkhakimov.quotes.data.repository

import com.timkhakimov.quotes.data.model.QuoteInfo

interface QuotesObserver {
    fun onQuotesUpdated(quotes: List<QuoteInfo>)
}
