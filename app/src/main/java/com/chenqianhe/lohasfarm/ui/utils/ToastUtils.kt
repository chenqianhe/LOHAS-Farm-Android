package com.chenqianhe.lohasfarm.ui.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast

private var LfToast: Toast? = null


fun showToast(context: Context?, text: String?) {
    if (context == null || TextUtils.isEmpty(text)) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, text, Toast.LENGTH_SHORT)
    } else {
        Handler(context.mainLooper).post { showToast(context, text, Toast.LENGTH_SHORT) }
    }
}

fun showLongToast(context: Context?, text: String?) {
    if (context == null || TextUtils.isEmpty(text)) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, text, Toast.LENGTH_LONG)
    } else {
        Handler(context.mainLooper).post { showToast(context, text, Toast.LENGTH_LONG) }
    }
}

private fun showToast(context: Context?, text: String?, duration: Int) {
    if (TextUtils.isEmpty(text)) return
    cancelToast()
    if (LfToast == null) {
        LfToast = Toast.makeText(context, null as CharSequence?, duration)
    }
    LfToast?.apply {
        setText(text)
        this.duration = duration
        show()
    }
}

fun cancelToast() {
    LfToast?.cancel()
}