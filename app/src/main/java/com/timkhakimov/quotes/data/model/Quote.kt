package com.timkhakimov.quotes.data.model

import com.google.gson.annotations.SerializedName

data class Quote(

    @SerializedName("c")
    val ticker: String?,

    @SerializedName("pcp")
    val pcp: Double?,               //Изменение в процентах относительно цены закрытия предыдущей торговой сессии

    @SerializedName("ltr")
    val ltr: String?,               //Биржа последней сделки

    @SerializedName("name")
    val name: String?,              //Название бумаги

    @SerializedName("ltp")
    val ltp: Double?,               //Цена последней сделки

    @SerializedName("chg")
    val chg: Double?                //Изменение цены последней сделки в пунктах относительно цены закрытия предыдущей торговой сессии
)
