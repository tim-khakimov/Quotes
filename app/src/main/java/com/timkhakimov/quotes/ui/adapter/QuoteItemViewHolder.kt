package com.timkhakimov.quotes.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.timkhakimov.quotes.Constants
import com.timkhakimov.quotes.R
import com.timkhakimov.quotes.data.model.Quote
import com.timkhakimov.quotes.data.model.QuoteInfo
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

    fun bind(quoteInfo: QuoteInfo) {
        setQuote(quoteInfo.quote)
        setPcp(quoteInfo.quote.pcp, quoteInfo.previousPcp)
    }

    private fun setQuote(quote: Quote) {
        setTicker(quote.ticker)
        setLtrAndName(quote.ltr, quote.name)
        setLtpAndChg(quote.ltp, quote.chg, quote.minStep)
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

    private fun setLtpAndChg(ltr: Double, chg: Double, minStep: Double) {
        //todo округление
        val roundedLtr = ltr
        val roundedChg = chg
        rootView.tvLtpAndChg.text = rootView.resources.getString(
            R.string.ltp_and_chg_mask,
            roundedLtr.toString(),
            roundedChg.toString()
        )
    }

    private fun setPcp(pcp: Double, previousPcp: Double) {
        rootView.tvPcp.text = rootView.resources.getString(R.string.pcp_mask, getPcpText(pcp))
        rootView.tvPcp.setTextColor(rootView.resources.getColor(getPcpTextColor(pcp, previousPcp)))
        rootView.tvPcp.setBackgroundDrawable(getPcpBackground(pcp, previousPcp))
    }

    private fun getPcpText(pcp: Double): String {
        return if(pcp > 0) {
            "+$pcp"
        } else {
            pcp.toString()
        }
    }

    @ColorRes
    private fun getPcpTextColor(pcp: Double, previousPcp: Double): Int {
        if(pcp != previousPcp) {
            return R.color.white
        }
        return if(pcp > 0) {
            R.color.green
        } else {
            R.color.red
        }
    }

    private fun getPcpBackground(pcp: Double, previousPcp: Double): Drawable? {
        if(pcp == previousPcp) {
            return null
        }
        return rootView.resources.getDrawable(
            if(pcp > previousPcp) {
                R.drawable.green_pcp_background
            } else {
                R.drawable.red_pcp_background
            }
        )
    }
}