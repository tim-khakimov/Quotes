package com.timkhakimov.quotes.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timkhakimov.quotes.data.model.Quote

class QuotesAdapter : RecyclerView.Adapter<QuoteItemViewHolder>() {

    private val data = mutableListOf<Quote>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        return QuoteItemViewHolder.create(parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setQuotes(quotes: List<Quote>) {
        data.clear()
        data.addAll(quotes)
        notifyDataSetChanged()
    }
}
