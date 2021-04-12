package com.example.myapplication.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R

class MainAdapter(): BaseQuickAdapter<Class<*>,BaseViewHolder>(R.layout.item_main){

    override fun convert(holder: BaseViewHolder, item: Class<*>) {
        holder.setText(R.id.tv_name,item.name)
    }

}