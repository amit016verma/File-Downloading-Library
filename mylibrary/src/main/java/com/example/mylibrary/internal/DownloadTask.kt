package com.example.mylibrary.internal

import com.example.mylibrary.httpclient.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DownloadTask(private val request: DownloadRequest, private val httpClient: HttpClient) {

    suspend fun run(
        onStart: () -> Unit = {},
        onProgress: (value: Int) -> Unit = { _ -> },
        onPause: () -> Unit = {},
        onComplete: () -> Unit = {},
        onError: (error: String) -> Unit = { _ -> },
    ) {
        withContext(Dispatchers.IO) {
            // dummy code for downloading the file
            onStart()

            // use of HttpClient
            httpClient.connect(request)

            for (i in 0..100) {
                delay(100)
                onProgress(i)
            }

            onComplete()
        }
    }
}