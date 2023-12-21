package com.example.mylibrary.httpclient

import com.example.mylibrary.Constants
import com.example.mylibrary.internal.DownloadRequest
import java.net.URL
import java.util.*

class DefaultHttpClient  : HttpClient{

    override fun connect(req: DownloadRequest) {

        /*val range: String = String.format(
            Locale.ENGLISH, "bytes=%d-", req.downloadedBytes
        )

        val connection = URL(req.url).openConnection()

        connection.apply {
            readTimeout = req.readTimeOut
            connectTimeout = req.connectTimeOut
            addRequestProperty(Constants.RANGE, range)
        }

        connection.connect()*/
    }
}