package com.timkhakimov.quotes.data

object Hardcode {

    private const val TICKERS = "RSTI,GAZP,MRKZ,RUAL,HYDR,MRKS,SBER,FEES,TGKA,VTBR,ANH.US,VICL.US,BURG.US,NBL.US,YETI.US,WSFS.US,NIO.US,DXC.US,MIC.US,HSBC.US,EXPN.EU,GSK.EU,SHP.EU,MAN.EU,DB1.EU,MUV2.EU,TATE.EU,KGF.EU,MGGT.EU,SGGD.EU"

    fun getTickers() : List<String> {
        return TICKERS.split(",").toList()
    }
}