package com.furuiproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.furuiproject.entity.GoodsItemEntity
import com.furuiproject.utils.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods.*

/**
 * 识别的商品界面
 */
class GoodsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)

        initView()

    }

    /**
     * // 左闭右开区间，合法值包括11，但不包括66
     * for (i in 11 until 66) { ... }
     * // 每次默认递增1，这里改为每次递增4
     * for (i in 23..89 step 4) { ... }
     * // for循环默认递增，这里使用downTo表示递减
     * for (i in 50 downTo 7) { ... }
     */

    private fun initView() {
        val layoutManager = LinearLayoutManager(this)
        rv_goods_data.layoutManager = layoutManager

        val dataList = ArrayList<GoodsItemEntity>()

        val goodsAdapter = GoodsAdapter(dataList)
        goodsAdapter.setDuration(500)
        goodsAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT)

        rv_goods_data.adapter = goodsAdapter

        for (i in 0 until 10) {
            dataList.add(GoodsItemEntity(Type = "瓜子种类$i", number = i, price = i.toFloat()))
        }

        goodsAdapter.notifyDataSetChanged()




        btn_add.setOnClickListener {
//            dataList.add(GoodsItemEntity(Type = "瓜子种类test", number = 0, price = 0.toFloat()))
//            goodsAdapter.notifyDataSetChanged()
//            rv_goods_data.scrollToPosition(dataList.size-1)

            startActivity(Intent(this,PayActivity::class.java))

        }
    }
}
