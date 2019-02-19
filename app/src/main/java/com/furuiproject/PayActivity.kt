package com.furuiproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pay.*

class PayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
        initView()
    }

    private fun initView() {
        btn_pay.setOnClickListener {
            val dialog = PaySuccessDialog(this)
            dialog.show()
        }
    }
}
