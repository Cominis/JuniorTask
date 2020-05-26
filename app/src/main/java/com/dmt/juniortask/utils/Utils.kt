package com.dmt.juniortask.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

suspend fun hasInternet() : Boolean {
    return withContext(Dispatchers.IO) {
        try {
            val timeoutMs = 3000
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)

            socket.connect(socketAddress, timeoutMs)
            socket.close()

            true
        }
        catch(ex: IOException) {
            false
        }
    }
}