package com.furuiproject

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.furuiproject.view.CountDownView
import com.furuiproject.view.WelcomeView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 流程 =>
 *
 *  等待检测=> 检测到物品=> 拍照上传 => 等待中 =>识别数据展示(无异常)
 *  => 进入付款页面 => 付款成功=>欢迎下次再来
 */
class MainActivity : AppCompatActivity() {

    private var welcomeView: WelcomeView? = null
    private var countTimeView: CountDownView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {

        addWelcomeView()

        main_show.setOnClickListener {
            startActivity(Intent(this,GoodsActivity::class.java))
//            removeWelcomeView()
//            addCountTimeView()
        }

        main_hidden.setOnClickListener {
            removeCountTimeView()
            addWelcomeView()
        }

    }

    /**
     *   val transition = LayoutTransition()
     *  val addAnimator = ObjectAnimator.ofFloat(null, "rotationY", 0f, 90f, 180f)
     *   .setDuration(transition.getDuration(LayoutTransition.APPEARING))
     *  transition.setAnimator(LayoutTransition.APPEARING, addAnimator)
     *  transition.setDuration(3000)
     *  main_root.layoutTransition = transition
     *
     *  添加欢迎页面
     */
    private fun addWelcomeView() {
        welcomeView = WelcomeView(this)
        val transition = LayoutTransition()
        val holder = PropertyValuesHolder.ofFloat(View.ALPHA, 0.0f, 1f)
        val scaleInX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.2f, 1f)
        val scaleInY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.2f, 1f)
        val valueAnimator = ObjectAnimator.ofPropertyValuesHolder(welcomeView, holder, scaleInX, scaleInY)
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, valueAnimator)
        transition.setDuration(500)
        main_root.layoutTransition = transition

        main_root.addView(welcomeView)
    }


    /**
     * 移除欢迎页面
     */
    private fun removeWelcomeView() {
        if (welcomeView != null) {
            main_root.removeView(welcomeView)
        }
    }


    /**
     * 添加倒计时页面
     */
    private fun addCountTimeView() {
        countTimeView = CountDownView(this)
        main_root.addView(countTimeView)
    }

    /**
     * 移除倒计时页面
     */
    private fun removeCountTimeView() {
        if (countTimeView != null) {
            main_root.removeView(countTimeView)
        }
    }


}
