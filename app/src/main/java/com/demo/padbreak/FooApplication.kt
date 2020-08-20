package com.demo.padbreak

import android.app.Application
import android.content.Context
import com.leiiiooo.breakpad.BreakPadHelper

/**
 * @author leiiiooo
 * @date 2020/8/20
 */
class FooApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        //choose your folder path
        base?.cacheDir?.absolutePath?.let {
            BreakPadHelper.initBreakPad(it)
        }
    }
}