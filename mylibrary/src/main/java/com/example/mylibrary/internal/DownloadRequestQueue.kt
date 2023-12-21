package com.example.mylibrary.internal

class DownloadRequestQueue(private val dispatchers: DownloadDispatchers) {

    private val idRequestMap: HashMap<Int, DownloadRequest> = hashMapOf()

    fun enqueue(request: DownloadRequest): Int {
        idRequestMap[request.downloadId] = request
        return dispatchers.enqueue(request)
    }

    fun pause(id: Int) {
        idRequestMap[id]?.let {
            dispatchers.cancel(it)
        }
    }

    fun resume(id: Int) {
        idRequestMap[id]?.let {
            dispatchers.enqueue(it)
        }
    }

    fun cancel(id: Int) {
        idRequestMap[id]?.let {
            dispatchers.cancel(it)
        }
        idRequestMap.remove(id)
    }

    fun cancel(tag: String?) {
        val requestWithTag = idRequestMap.values.filter { it.tag == tag }

        for (req in requestWithTag) {
            cancel(req.downloadId)
        }
    }

    fun cancelAll(){
        idRequestMap.clear()
        dispatchers.cancelAll()
    }
}