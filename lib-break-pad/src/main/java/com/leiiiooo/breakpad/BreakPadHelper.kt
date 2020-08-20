package com.leiiiooo.breakpad

/**
 * @author leiiiooo
 * @date 2020/8/20
 */
class BreakPadHelper {
    companion object {
        init {
            System.loadLibrary("breakpad-lib")
        }

        fun initBreakPad(path: String) = initBreakPadNative(path)
        
        private external fun initBreakPadNative(path: String)
    }
}