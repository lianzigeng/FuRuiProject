package com.furuiproject.utils

import com.furuiproject.view.WelcomeView

class InterfaceManager {
    interface WelcomeViewListener {

        /**
         *bool :true ,展示view, false:隐藏view
         */
        fun showOrHideWelcomeView(view: WelcomeView, bool: Boolean)
    }
}