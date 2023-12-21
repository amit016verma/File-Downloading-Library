package com.example.filedownloadinglib

import android.app.Application
import com.example.mylibrary.Downloader

class MyApplication : Application() {

    lateinit var downloader: Downloader

    override fun onCreate() {
        super.onCreate()
        downloader = Downloader.create()
    }
}