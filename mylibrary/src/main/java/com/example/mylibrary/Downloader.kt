package com.example.mylibrary

import com.example.mylibrary.internal.DownloadDispatchers
import com.example.mylibrary.internal.DownloadRequest
import com.example.mylibrary.internal.DownloadRequestQueue

class Downloader constructor(private val downloaderConfig: DownloaderConfig) {

    companion object {

        fun create(downloaderConfig: DownloaderConfig = DownloaderConfig()): Downloader {
            return Downloader(downloaderConfig)
        }
    }

    private var reqQueue = DownloadRequestQueue(DownloadDispatchers(downloaderConfig.httpClient))

    fun newReqBuilder(url: String, dirPath: String, fileName: String): DownloadRequest.Builder {
        return DownloadRequest.Builder(url = url, dirPath = dirPath, fileName = fileName)
            .readTimeOut(downloaderConfig.readTimeOut)
            .connectTimeOut(downloaderConfig.connectTimeOut)
    }

    fun enqueue(
        request: DownloadRequest,
        onStart: () -> Unit = {},
        onProgress: (value: Int) -> Unit = { _ -> },
        onPause: () -> Unit = {},
        onComplete: () -> Unit = {},
        onError: (error: String) -> Unit = { _ -> }

    ): Int {
        request.onStart = onStart
        request.onProgress = onProgress
        request.onPause = onPause
        request.onComplete = onComplete
        request.onError = onError

        return reqQueue.enqueue(request)
    }

    fun pause(id: Int) {
        reqQueue.pause(id)
    }

    fun resume(id: Int) {
        reqQueue.resume(id)
    }

    fun cancel(id: Int) {
        reqQueue.cancel(id)
    }

    fun cancel(tag: String) {
        reqQueue.cancel(tag)
    }

    fun cancelAll() {
        reqQueue.cancelAll()
    }
}