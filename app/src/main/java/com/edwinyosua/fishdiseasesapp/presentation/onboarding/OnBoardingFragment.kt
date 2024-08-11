package com.edwinyosua.fishdiseasesapp.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.R
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.databinding.FragmentOnBoardingBinding
import org.koin.android.ext.android.inject

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {


    private val onBoardingViewModel: OnBoardingViewModel by inject()


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

        //why is this still called after login button pressed ?
        onBoardingViewModel.checkLogin().observe(requireActivity()) { userId ->
            if (userId != null) {
                findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
            }

        }
    }
}