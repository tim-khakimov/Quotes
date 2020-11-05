package com.timkhakimov.quotes.data.repository

import com.timkhakimov.quotes.data.model.Quote
import com.timkhakimov.quotes.data.model.QuoteInfo

class QuotesStorage {

    private val data = mutableListOf<QuoteInfo>()

    fun setQuotes(quotes: List<Quote>) {
        for (quote in quotes) {
            addOrReplaceQuote(quote)
        }
    }

    fun getData(): List<QuoteInfo> {
        return data
    }

    private fun addOrReplaceQuote(quote: Quote) {
        val currentQuote = data.find { it.quote.ticker == quote.ticker }
        if (currentQuote != null) {
            val previousPcp = currentQuote.quote.pcp
            val index = data.indexOf(currentQuote)
            data[index] = QuoteInfo(quote, previousPcp)
        } else {
            data.add(QuoteInfo(quote, quote.pcp))
        }
    }
}
