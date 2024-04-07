package com.weijun.assuarancetechnologyassessment.components

import android.os.Build
import androidx.annotation.RequiresApi

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class TriangleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint: Paint = Paint()
    private val path: Path = Path()

    init {
        paint.apply {
            color = resources.getColor(android.R.color.white)
            style = Paint.Style.FILL
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDraw(canvas: Canvas) {
        path.moveTo(0f, height/2f)
        path.lineTo(0f, height.toFloat())
        path.lineTo(width.toFloat(), height.toFloat())
        path.close()

        canvas.drawPath(path, paint)
    }
}