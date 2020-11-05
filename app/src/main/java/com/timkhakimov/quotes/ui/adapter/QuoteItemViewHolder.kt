package com.timkhakimov.quotes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.timkhakimov.quotes.Constants
import com.timkhakimov.quotes.R
import com.timkhakimov.quotes.data.model.Quote
import kotlinx.android.synthetic.main.item_quote.view.*

class QuoteItemViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

    companion object {
        fun create(viewGroup: ViewGroup): QuoteItemViewHolder {
            return QuoteItemViewHolder(
                LayoutInflater
                    .from(viewGroup.context)
                    .inflate(
                        R.layout.item_quote,
                        viewGroup,
                        false
                    )
            )
        }
    }

    fun bind(quote: Quote) {
        setTicker(quote.ticker)
        setLtrAndName(quote.ltr, quote.name)
    }

    private fun setTicker(ticker: String?) {
        ticker?.let {
            setImage(it)
            rootView.tvTicker.text = it
        }
    }

    private fun setImage(ticker: String) {
        val url = Constants.LOGO_URL_PREFIX + ticker.toLowerCase()
        Picasso.get()
            .load(url)
            .into(rootView.ivLogo)
    }

    private fun setLtrAndName(ltr: String?, name: String?) {
        when {
            ltr != null && name != null -> {
                rootView.tvLtrAndName.text =
                    rootView.resources.getString(R.string.ltr_and_name_mask, ltr, name)
            }
            ltr != null -> rootView.tvLtrAndName.text = ltr
            name != null -> rootView.tvLtrAndName.text = name
            else -> rootView.tvLtrAndName.text = ""
        }
    }

    private fun setPcp(pcp: Double) {
        rootView.tvPcp.text = pcp.toString()        //todo цвета
    }

    private fun setLtpAndChg(ltr: Double, chg: Double) {
        rootView.tvLtpAndChg.text = rootView.resources.getString(
            R.string.ltp_and_chg_mask,
            ltr,
            chg
        )       //todo округление
    }
}