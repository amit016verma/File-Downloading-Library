package com.example.mylibrary.httpclient

import com.example.mylibrary.internal.DownloadRequest

interface HttpClient {

    fun connect(downloadRequest: DownloadRequest)
}