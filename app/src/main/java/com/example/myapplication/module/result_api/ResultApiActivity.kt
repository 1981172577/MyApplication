package com.example.myapplication.module.result_api

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_result_api.*

class ResultApiActivity : BaseActivity(R.layout.activity_result_api){

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // 处理返回的 Uri
        showToast(uri?.path)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun changePage(view :View){
        // 传入您想让用户选择的 mime 类型作为输入
        getContent.launch("image/*")
    }
}