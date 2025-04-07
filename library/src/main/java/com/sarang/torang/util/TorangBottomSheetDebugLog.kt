package com.sarang.torang.util

import android.util.Log

class TorangBottomSheetDebugLog {
    companion object {
        val debug = false
        fun d(tag: String?, msg: String) {
            if (debug)
                Log.d(tag, msg)
        }

    }
}