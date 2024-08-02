package com.edwinyosua.fishdiseasesapp.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.edwinyosua.fishdiseasesapp.R

class CustomButtonMain : AppCompatButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var txtColor: Int = 0
    private var txtColorPressed: Int = 0
    private var defaultBtnBackground: Drawable
    private var pressedBtnBackground: Drawable
    private var disabledBtnBackground: Drawable

    init {

        txtColor = ContextCompat.getColor(context, R.color.white)
        txtColorPressed = ContextCompat.getColor(context, R.color.btnTextColor)
        defaultBtnBackground =
            ContextCompat.getDrawable(context, R.drawable.bg_button_main) as Drawable
        pressedBtnBackground =
            ContextCompat.getDrawable(context, R.drawable.bg_btn_main_pressed) as Drawable
        disabledBtnBackground =
            ContextCompat.getDrawable(context, R.drawable.bg_btn_main_disabled) as Drawable
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//
//        background = defaultBtnBackground
//        if (isPressed) setTextColor(txtColorPressed) else setTextColor(txtColor)

        if (isPressed) {
            background = pressedBtnBackground
            setTextColor(txtColorPressed)
        } else if (!isEnabled) {
            background = disabledBtnBackground
            setTextColor(txtColor)
        } else if (isEnabled) {
            background = defaultBtnBackground
            setTextColor(txtColor)
        }

        textAlignment = TEXT_ALIGNMENT_CENTER


    }
}