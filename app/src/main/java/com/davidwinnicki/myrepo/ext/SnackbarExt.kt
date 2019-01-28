package com.davidwinnicki.myrepo.ext

import android.support.design.widget.Snackbar
import android.view.View

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, length).show()
}
