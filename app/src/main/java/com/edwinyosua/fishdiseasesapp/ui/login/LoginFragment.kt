package com.edwinyosua.fishdiseasesapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.R
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.databinding.FragmentLoginBinding
import es.dmoral.toasty.Toasty
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val loginViewModel: LoginViewModel by inject()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun initInsets(): Boolean = false

    override fun initKeyboardHandler(): Boolean = true

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        binding.apply {
            btnLogin.setOnClickListener {
                if (editEmail.text?.isNotEmpty() == true && editPassword.text?.isNotEmpty() == true) {
                    loginViewModel.login(
                        editEmail.text.toString().trim(),
                        editPassword.text.toString().trim()
                    )
                }
            }
            btnSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            txtForgot.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_accountRecoveryFragment)
            }
        }
    }

    override fun initProcess() {
        binding.apply {
            loginViewModel.loginResult.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ApiResult.Success -> {
                        progBar.hide()
                        Toasty.success(requireContext(), "You are logged in !", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        disableButton(true)
                    }

                    is ApiResult.Error -> {
                        progBar.hide()
                        Toasty.error(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                        disableButton(true)
                    }

                    ApiResult.Loading -> {
                        progBar.show()
                        disableButton(false)
                    }
                }
            }
        }
    }

    override fun initObservers() {}

    private fun disableButton(enabled: Boolean) {
        binding.apply {
            btnLogin.isEnabled = enabled
            btnSignUp.isEnabled = enabled
        }
    }
}