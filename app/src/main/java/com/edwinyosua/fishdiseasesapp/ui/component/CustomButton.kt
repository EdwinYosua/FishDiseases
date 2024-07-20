package com.edwinyosua.fishdiseasesapp.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.edwinyosua.fishdiseasesapp.R

class CustomButton : AppCompatButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var txtColor: Int = 0
    private var txtColorPressed: Int = 0
    private var defaultBtnBackground: Drawable

    init {
        txtColor = ContextCompat.getColor(context, R.color.btnTextColor)
        txtColorPressed = ContextCompat.getColor(context, R.color.white)
        defaultBtnBackground =
            ContextCompat.getDrawable(context, R.drawable.bg_btn) as Drawable
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        background = defaultBtnBackground
        if (isPressed) setTextColor(txtColorPressed) else setTextColor(txtColor)
        gravity = Gravity.CENTER

    }


}