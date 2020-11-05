package com.timkhakimov.quotes.ui.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timkhakimov.quotes.data.model.Quote
import com.timkhakimov.quotes.data.model.QuoteInfo

class QuotesAdapter : RecyclerView.Adapter<QuoteItemViewHolder>() {

    private val data = mutableListOf<QuoteInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        return QuoteItemViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setQuotes(quotes: List<QuoteInfo>) {
        Log.d("QuotesAdapter", "setQuotes: " + quotes.size)
        data.clear()
        data.addAll(quotes)
        notifyDataSetChanged()
    }
}
