package com.edwinyosua.fishdiseasesapp.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.R
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.data.local.SettingPreference
import com.edwinyosua.fishdiseasesapp.databinding.FragmentOnBoardingBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {


    private val pref: SettingPreference by inject()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOnBoardingBinding = FragmentOnBoardingBinding.inflate(inflater, container, false)

    override fun initInsets(): Boolean = false

    override fun initKeyboardHandler(): Boolean = false

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        binding.apply {
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardingFragment_to_registerFragment)
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {

        lifecycleScope.launch {
            if (pref.getUserId()?.isNotEmpty() == true) {
                findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
            }
        }
    }
}