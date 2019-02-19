package com.furuiproject

import android.app.Dialog
import android.content.Context
import android.os.Bundle

class PaySuccessDialog : Dialog {

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setWindowAnimations(R.style.DialogStyle)
        window.setBackgroundDrawableResource(android.R.color.transparent)
        setContentView(R.layout.dialog_pay_success)
//        todo 这个应该 进行相应的修改
        setCanceledOnTouchOutside(true)
    }

}