package com.example.mylibrary

import com.example.mylibrary.httpclient.DefaultHttpClient
import com.example.mylibrary.httpclient.HttpClient

data class DownloaderConfig(
    val httpClient: HttpClient = DefaultHttpClient(),
    val readTimeOut : Int = Constants.DEFAULT_READ_TIME_OUT,
    val connectTimeOut : Int = Constants.DEFAULT_CONNECT_TIME_OUT
)
