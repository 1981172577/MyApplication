package com.example.myapplication.module.thread

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_test_kotlin.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestKotlin : AppCompatActivity(R.layout.activity_test_kotlin) {

    lateinit var myHandler:Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handlerThread:HandlerThread = HandlerThread("test",0)
        handlerThread.start()

        Log.d("----",Thread.currentThread().name)
        myHandler = Handler(handlerThread.looper, Handler.Callback {
            Log.d("----",Thread.currentThread().name)
            return@Callback false
        })
        test1()
    }

    @SuppressLint("SetTextI18n")
    fun test1(){
        val job = lifecycleScope.launch {
            delay(3000)
            tv.text = "Hello World"
        }
        myHandler.sendEmptyMessage(1)
    }
}