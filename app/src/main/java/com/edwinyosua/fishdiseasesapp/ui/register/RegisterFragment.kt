package com.edwinyosua.fishdiseasesapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.R
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.databinding.FragmentRegisterBinding


class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun initInsets(): Boolean = false

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        binding.apply {
            btnSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            txvLinkLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}