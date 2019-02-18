package com.furuiproject

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.furuiproject.Entity.GoodsItemEntity

class GoodsAdapter : BaseQuickAdapter<GoodsItemEntity, BaseViewHolder>{

    constructor(data:ArrayList<GoodsItemEntity>):super(R.layout.layout_goods_item)

    override fun convert(helper: BaseViewHolder?, item: GoodsItemEntity?) {
    }

}


