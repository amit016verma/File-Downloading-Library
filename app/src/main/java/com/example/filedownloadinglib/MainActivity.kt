package com.example.filedownloadinglib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filedownloadinglib.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val downloader = (applicationContext as MyApplication).downloader

        val request =
            downloader.newReqBuilder("someUrl", "someDirPath", "someFileName").readTimeOut(10000)
                .connectTimeOut(1000).tag("someTag").build()

        downloader.enqueue(request = request, onStart = {
            binding.textViewStatus.text = "onStart"
        }, onProgress = {
            binding.textViewProgress.text = "$it %"
            binding.progressBar.progress = it
        }, onComplete = {
            binding.textViewStatus.text = "onComplete"
        }, onPause = {
            binding.textViewStatus.text = "onPause"
        }, onError = {
            binding.textViewStatus.text = it
        })
    }
}