package com.edwinyosua.fishdiseasesapp.presentation.onboarding

import android.os.Bundle
import android.util.Log
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

        onBoardingViewModel.checkToken().observe(viewLifecycleOwner) { token ->
            when (token) {
                null -> {
                    Log.d("TokenCheck", "OnboardingFragment : ${token}")
                }

                else -> {
                    Log.d("TokenCheck", "OnBoardingFragment : $token")
                    findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
                }
            }

        }

    }
}