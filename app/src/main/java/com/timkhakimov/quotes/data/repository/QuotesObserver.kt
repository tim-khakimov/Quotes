package com.timkhakimov.quotes.data.repository

import com.timkhakimov.quotes.data.model.Quote

interface QuotesObserver {
    fun onQuotesUpdated(quotes: List<Quote>)
}
