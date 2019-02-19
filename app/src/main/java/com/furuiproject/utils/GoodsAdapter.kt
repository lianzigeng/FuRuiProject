package com.furuiproject.utils

import android.animation.Animator
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.furuiproject.R
import com.furuiproject.entity.GoodsItemEntity

class GoodsAdapter : BaseQuickAdapter<GoodsItemEntity, BaseViewHolder> {

    constructor(data: ArrayList<GoodsItemEntity>) : super(R.layout.layout_goods_item, data)

    override fun convert(helper: BaseViewHolder?, item: GoodsItemEntity?) {
        helper!!.setText(R.id.tv_goods_type, item!!.Type)
        helper.setText(R.id.tv_goods_number, "${item.number}")
        helper.setText(R.id.tv_goods_price, "${item.price}")
    }

    override fun startAnim(anim: Animator?, index: Int) {
        super.startAnim(anim, index)
        anim!!.startDelay = (index * 350).toLong()
    }

}


