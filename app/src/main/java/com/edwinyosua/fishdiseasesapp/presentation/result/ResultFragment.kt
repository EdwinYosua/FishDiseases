package com.edwinyosua.fishdiseasesapp.presentation.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.databinding.FragmentResultBinding

class ResultFragment : BaseFragment<FragmentResultBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentResultBinding = FragmentResultBinding.inflate(layoutInflater, container, false)

    override fun initInsets(): Boolean = true

    override fun initKeyboardHandler(): Boolean = false

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}