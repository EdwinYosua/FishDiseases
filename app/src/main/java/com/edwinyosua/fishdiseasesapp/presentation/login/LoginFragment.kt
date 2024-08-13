package com.edwinyosua.fishdiseasesapp.presentation.login

import android.os.Bundle
import android.util.Patterns
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

                val email = editEmail.text?.trim().toString()
                val pass = editPassword.text?.trim().toString()

                if (pass.isEmpty()) toastyMsg("Field Password is Empty !", 2)
//                    Toasty.error(requireContext(), "Field Password is Empty !", Toast.LENGTH_SHORT).show()

                if (email.isEmpty()) toastyMsg("Field Email is Empty !", 2)
//                    Toasty.error(requireContext(),"Field Email is Empty !", Toast.LENGTH_SHORT).show()


                if (
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                    email.isNotEmpty()
                ) toastyMsg("Wrong Email Format !", 2)
//                    Toasty.error(requireContext(), "Wrong Email Format !", Toast.LENGTH_SHORT).show()

                if ((email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) &&
                    pass.isNotEmpty()
                ) {
                    loginViewModel.login(email, pass)
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
                        toastyMsg("You are Logged in !", 1)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        disableButton(true)
                    }

                    is ApiResult.Error -> {
                        progBar.hide()
//                        Toasty.error(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                        toastyMsg(result.error, 0)
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

    private fun toastyMsg(msg: String, status: Int) {

        Toasty.Config.getInstance()
            .allowQueue(false)
            .apply()


        when (status) {

            0 -> Toasty.error(requireContext(), msg, Toast.LENGTH_SHORT).show()
            1 -> Toasty.success(requireContext(), msg, Toast.LENGTH_SHORT).show()
            2 -> Toasty.info(requireContext(), msg, Toast.LENGTH_SHORT).show()
            3 -> Toasty.warning(requireContext(), msg, Toast.LENGTH_SHORT).show()
            else -> Toasty.normal(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }


//        if (success) {
//            Toasty.success(requireContext(), msg, Toast.LENGTH_SHORT).show()
//        }
//
//        if (!success) {
//            Toasty.error(requireContext(), msg, Toast.LENGTH_SHORT).show()
//        }
    }

    private fun disableButton(enabled: Boolean) {
        binding.apply {
            btnLogin.isEnabled = enabled
            btnSignUp.isEnabled = enabled
        }
    }
}