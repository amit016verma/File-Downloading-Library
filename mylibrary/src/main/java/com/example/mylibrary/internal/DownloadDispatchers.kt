package com.example.mylibrary.internal

import com.example.mylibrary.httpclient.HttpClient
import kotlinx.coroutines.*

class DownloadDispatchers(private val httpClient: HttpClient) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun enqueue(req: DownloadRequest): Int {
        val job = scope.launch {
            execute(req)
        }
        req.job = job
        return req.downloadId
    }

    private suspend fun execute(request: DownloadRequest) {
        DownloadTask(request, httpClient).run(onStart = {
            executeOnMainThread {
                request.onStart()
            }
        }, onProgress = {
            executeOnMainThread {
                request.onProgress(it)
            }
        }, onPause = {
            executeOnMainThread {
                request.onPause()
            }
        }, onComplete = {
            executeOnMainThread {
                request.onComplete()
            }
        }, onError = {
            executeOnMainThread {
                request.onError(it)
            }
        }
        )
    }

    private fun executeOnMainThread(block: () -> Unit) {
        scope.launch {
            block()
        }
    }

    fun cancel(req: DownloadRequest) {
        req.job.cancel()
    }

    fun cancelAll() {
        scope.cancel()
    }
}