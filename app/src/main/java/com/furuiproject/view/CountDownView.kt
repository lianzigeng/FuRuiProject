package com.furuiproject.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.furuiproject.R
import com.orhanobut.logger.Logger

/**
 * 倒计时视图
 *
 *  主要有倒计时的作用
 */
class CountDownView : RelativeLayout {

    private lateinit var countTv: TextView
    private var countNumber: Int = 0

    private lateinit var timeHandler: Handler

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {
        val rootView = LayoutInflater.from(context).inflate(R.layout.view_count_down, this)

        countTv = rootView.findViewById<TextView>(R.id.tv_count_number)
        countNumber = (Math.random() * 10).toInt() + 5
        Logger.d("获取到的 number => $countNumber")
       Logger.d("线程名字=> ${ Thread.currentThread().name}")
        countTv.text = "$countNumber"
        timeHandler = Handler(Looper.getMainLooper())
        timeHandler.postDelayed(timeRunnable, 1000)
    }

    private var timeRunnable: Runnable = Runnable {
        if (countNumber < 0) return@Runnable
        Logger.d("线程名字2 => ${ Thread.currentThread().name}")
        countNumber--
        countTv.text = "$countNumber"
        if (countNumber === 0) {
            Logger.d("计算完毕")
            timeHandler.removeCallbacksAndMessages(null)
        } else {
            Logger.d("$countNumber")
            timeHandler.post(timeRunnable2)
        }
    }

    private var timeRunnable2: Runnable = Runnable {
        timeHandler.postDelayed(timeRunnable, 1000)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        timeHandler.removeCallbacksAndMessages(null)
    }
}