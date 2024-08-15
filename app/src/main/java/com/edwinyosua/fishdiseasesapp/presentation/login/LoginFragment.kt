package com.edwinyosua.fishdiseasesapp.presentation.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.R
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.databinding.FragmentLoginBinding
import com.edwinyosua.fishdiseasesapp.utils.ext.isEmailValid
import com.edwinyosua.fishdiseasesapp.utils.ext.toastyMsg
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

                val email = editEmail.text?.trim().toString()
                val pass = editPassword.text?.trim().toString()



                if (pass.length < 8) toastyMsg(
                    requireContext(),
                    "Password is Less Than 8 Character",
                    2
                )

                if (pass.isEmpty()) toastyMsg(requireContext(), "Field Password is Empty !", 2)

                if (email.isEmpty()) toastyMsg(requireContext(), "Field Email is Empty !", 2)

                if (
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                    email.isNotEmpty()
                ) toastyMsg(requireContext(), "Wrong Email Format !", 2)




                when {
                    email.isEmailValid() && pass.length >= 8 -> {
                        loginViewModel.login(email, pass)
                    }
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
//                        Toasty.success(requireContext(), "You are logged in !", Toast.LENGTH_SHORT).show()
                        toastyMsg(requireContext(), "You are Logged in !", 1)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        disableButton(true)
                    }

                    is ApiResult.Error -> {
                        progBar.hide()
//                        Toasty.error(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                        toastyMsg(requireContext(), result.error, 0)
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