package com.timkhakimov.quotes.data.socket

import com.google.gson.Gson
import com.timkhakimov.quotes.data.Hardcode
import com.timkhakimov.quotes.data.model.QuotesData
import com.timkhakimov.quotes.data.repository.QuotesDataObservable
import com.timkhakimov.quotes.data.repository.QuotesDataObserver
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONArray
import org.json.JSONObject

private const val SOCKET_IO_URL = "https://ws3.tradernet.ru/"
private const val EVENT_NAME_TO_EMIT = "sup_updateSecurities2"
private const val EVENT_NAME_TO_LISTEN = "q"

class SocketWrapper : Emitter.Listener, QuotesDataObservable {

    private val socket: Socket
    private val gson = Gson()
    private var observer: QuotesDataObserver? = null

    init {
        socket = IO.socket(SOCKET_IO_URL)
    }

    override fun call(vararg args: Any?) {
        val obj = args[0] as JSONObject
        val string: String = obj.toString()
        val value : QuotesData = gson.fromJson(string, QuotesData::class.java)
        observer?.onGetQuotes(value)
    }

    override fun observeQuotesData(quotesDataObserver: QuotesDataObserver) {
        this.observer = quotesDataObserver
        socket.connect()
        socket.on(EVENT_NAME_TO_LISTEN, this)
        socket.emit(EVENT_NAME_TO_EMIT, JSONArray(Hardcode.getTickers()))
    }

    override fun stop() {
        socket.disconnect()
    }
}