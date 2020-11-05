package com.timkhakimov.quotes.data.repository

import com.timkhakimov.quotes.data.model.Quote

class QuotesStorage {

    private val data = mutableListOf<Quote>()

    fun setData(quotes: List<Quote>) {
        for (quote in quotes) {
            addOrReplaceQuote(quote)
        }
    }

    fun getData(): List<Quote> {
        return data
    }

    private fun addOrReplaceQuote(quote: Quote) {
        val currentQuote = data.find { it.ticker == quote.ticker }
        if (currentQuote != null) {
            val index = data.indexOf(currentQuote)
            data[index] = quote
        } else {
            data.add(quote)
        }
    }
}
