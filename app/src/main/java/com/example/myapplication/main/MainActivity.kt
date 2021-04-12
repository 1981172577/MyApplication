package com.example.myapplication.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.myapplication.PressFrameLayout.PressFrameActivity
import com.example.myapplication.R
import com.example.myapplication.WaveView.WaveActivity
import com.example.myapplication.module.canvas.CanvasActivity
import com.example.myapplication.module.files.FileTestActivity
import com.example.myapplication.module.paging.PagingActivity
import com.example.myapplication.module.result_api.ResultApiActivity
import com.example.myapplication.module.thread.TestKotlin
import com.example.myapplication.parallax.ParallaxActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity(R.layout.activity_main), OnItemClickListener {

    val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler_view.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
        mainAdapter.setOnItemClickListener(this)
        mainAdapter.setNewInstance(initClassName())
    }

    private fun initClassName() : ArrayList<Class<*>>{
        val classNames : ArrayList<Class<*>> = ArrayList()

        classNames.apply {
            add(ParallaxActivity::class.java)
            add(WaveActivity::class.java)
            add(PressFrameActivity::class.java)
            add(CanvasActivity::class.java)
            add(TestKotlin::class.java)
            add(FileTestActivity::class.java)
            add(ResultApiActivity::class.java)
            add(PagingActivity::class.java)
        }
        return classNames
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val cls = mainAdapter.getItem(position)
        startActivity(Intent(this,cls))
    }

}