package com.edwinyosua.fishdiseasesapp.ui.accountrecovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.databinding.FragmentAccountRecoveryBinding

class AccountRecoveryFragment : BaseFragment<FragmentAccountRecoveryBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAccountRecoveryBinding =
        FragmentAccountRecoveryBinding.inflate(inflater, container, false)

    override fun initInsets(): Boolean = false

    override fun initKeyboardHandler(): Boolean = true

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}


}