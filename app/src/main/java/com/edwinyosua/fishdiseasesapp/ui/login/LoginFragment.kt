package com.edwinyosua.fishdiseasesapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>() {


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun initInsets(): Boolean = false

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        binding.apply {
            btnLogin.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
            btnSignUp.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}