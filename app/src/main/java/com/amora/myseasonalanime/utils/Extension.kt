package com.amora.myseasonalanime.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.appToast(msg: CharSequence, isShort: Boolean = true) {
    if (!isShort) Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    else Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}