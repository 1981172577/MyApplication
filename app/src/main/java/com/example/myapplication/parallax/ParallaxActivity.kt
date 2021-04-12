package com.example.myapplication.parallax

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.seagazer.parallexdecoration.ParallaxDecoration
import kotlinx.android.synthetic.main.activity_parallax.*

class ParallaxActivity :AppCompatActivity(R.layout.activity_parallax) {

    private val bgs = intArrayOf(
        R.drawable.rd_gua_seed_1, R.drawable.rd_gua_seed_2, R.drawable.rd_gua_seed_3,
        R.drawable.rd_gua_seed_4, R.drawable.rd_gua_seed_5, R.drawable.rd_gua_seed_6
    )

    private lateinit var listAdapter: MyAdapter
    private var lastItemDecoration: RecyclerView.ItemDecoration? = null
    private var parallaxSize = 1f
    private var autoFillBitmap = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = MyAdapter()
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ParallaxActivity,RecyclerView.HORIZONTAL,false)
            adapter = listAdapter
            lastItemDecoration = ParallaxDecoration(this.context).apply {
                setupResource(bgs.asList())
                parallax = parallaxSize
                autoFill = autoFillBitmap
            }
            addItemDecoration(lastItemDecoration!!)
        }
        auto_fill.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {// autoFill
                updateItemDecoration(true)
            } else {// notFill
                updateItemDecoration(false)
            }
        }
        parallax.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    parallaxSize = progress * 1f / 100
                    current_parallax.text = "parallax:$parallaxSize"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                updateItemDecoration(autoFillBitmap)
            }

        })
    }

    private fun updateItemDecoration(isAutoFill: Boolean) {
        if (lastItemDecoration != null) {
            recycler_view.removeItemDecoration(lastItemDecoration!!)
        }
        autoFillBitmap = isAutoFill
        lastItemDecoration = ParallaxDecoration(this@ParallaxActivity).apply {
            setupResource(bgs.asList())
            parallax = parallaxSize
            autoFill = autoFillBitmap
        }
        recycler_view.addItemDecoration(lastItemDecoration!!)
    }

}