package com.furuiproject.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.furuiproject.R

/**
 * 欢迎视图
 */
class WelcomeView : RelativeLayout {


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {
        LayoutInflater.from(context).inflate(R.layout.view_welcome, this)

    }


}