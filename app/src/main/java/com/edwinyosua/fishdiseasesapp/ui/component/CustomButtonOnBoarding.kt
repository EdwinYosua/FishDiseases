package com.edwinyosua.fishdiseasesapp.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.edwinyosua.fishdiseasesapp.R

class CustomButtonOnBoarding : AppCompatButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var txtColor: Int = 0
    private var txtColorPressed: Int = 0
    private var defaultBtnBackground: Drawable
    private var pressedBtnBackground: Drawable

    init {
        txtColor = ContextCompat.getColor(context, R.color.btnTextColor)
        txtColorPressed = ContextCompat.getColor(context, R.color.white)
        defaultBtnBackground =
            ContextCompat.getDrawable(context, R.drawable.bg_btn_onboarding) as Drawable
        pressedBtnBackground =
            ContextCompat.getDrawable(context, R.drawable.bg_btn_onboarding_pressed) as Drawable
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isPressed) {
            background = pressedBtnBackground
            setTextColor(txtColorPressed)
        } else {
            background = defaultBtnBackground
            setTextColor(txtColor)
        }

        textAlignment = TEXT_ALIGNMENT_CENTER
    }


}