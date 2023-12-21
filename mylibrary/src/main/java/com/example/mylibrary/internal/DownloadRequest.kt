package com.example.mylibrary.internal

import com.example.mylibrary.utils.getUniqueId
import kotlinx.coroutines.Job

class DownloadRequest private constructor(
    internal var url: String,
    internal val tag: String?,
    internal val dirPath: String,
    internal val downloadId: Int,
    internal val fileName: String,
    internal var readTimeOut: Int,
    internal var connectTimeOut: Int
) {

    internal var totalBytes : Long = 0
    internal var downloadedBytes : Long = 0
    internal lateinit var job : Job
    internal lateinit var onStart : () -> Unit
    internal lateinit var onPause : () -> Unit
    internal lateinit var onProgress : (value : Int) -> Unit
    internal lateinit var onComplete : () -> Unit
    internal lateinit var onError : (error : String) -> Unit


    data class Builder(
        private val url: String, private val dirPath: String, private val fileName: String
    ) {
        private var tag: String? = null
        private var readTimeOut: Int = 0
        private var connectTimeOut: Int = 0

        fun tag(tag: String?) = apply {
            this.tag = tag
        }

        fun readTimeOut(timeOut: Int) = apply {
            this.readTimeOut = timeOut
        }

        fun connectTimeOut(timeOut: Int) = apply {
            this.connectTimeOut = timeOut
        }

        fun build(): DownloadRequest {
            return DownloadRequest(
                url = url,
                tag = tag,
                dirPath = dirPath,
                downloadId = getUniqueId(url = url, dirPath = dirPath, fileName = fileName),
                fileName = fileName,
                readTimeOut = readTimeOut,
                connectTimeOut = connectTimeOut
            )
        }
    }
}