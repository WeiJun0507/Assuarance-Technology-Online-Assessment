package com.weijun.assuarancetechnologyassessment.utilities

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar

class SnackBarManager {
    companion object {
        fun showErrorSnackBar(view: View, message: String) {
            val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            val snackBarView = snackBar.view
            val params = snackBarView.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            snackBarView.layoutParams = params
            snackBar.show()
        }
    }
}