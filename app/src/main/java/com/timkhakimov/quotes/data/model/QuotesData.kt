package com.timkhakimov.quotes.data.model

import com.google.gson.annotations.SerializedName

data class QuotesData(
    @SerializedName("q")
    val quotes: List<Quote>?
)
