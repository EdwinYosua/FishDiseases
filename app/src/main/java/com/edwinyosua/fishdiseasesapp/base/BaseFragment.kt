package com.edwinyosua.fishdiseasesapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container, savedInstanceState)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initInsets()
        initKeyboardHandler()
        initIntent()
        initUI()
        initAction()
        initProcess()
        initObservers()

        applyBottomTopInset(view, initInsets())
        handleKeyboardOverlap(view, initKeyboardHandler())
    }

    private fun handleKeyboardOverlap(view: View, applyKeyboardHandle: Boolean) {
        if (applyKeyboardHandle) {
            ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
                val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
                val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
                v.updatePadding(bottom = if (imeVisible) imeInsets.bottom else 0)
                insets
            }
        }
    }

    private fun applyBottomTopInset(view: View, applyInset: Boolean) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowsInsets ->
            val insets = windowsInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                if (applyInset) {
                    topMargin = insets.top
                    bottomMargin = insets.bottom
                }
            }
            WindowInsetsCompat.CONSUMED
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

//    should you add the inset function in here or nah ?

    abstract fun initInsets(): Boolean
    abstract fun initKeyboardHandler(): Boolean
    abstract fun initIntent()
    abstract fun initUI()
    abstract fun initAction()
    abstract fun initProcess()
    abstract fun initObservers()


}
