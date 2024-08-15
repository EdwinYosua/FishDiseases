package com.edwinyosua.fishdiseasesapp.presentation.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.R
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.databinding.FragmentRegisterBinding
import com.edwinyosua.fishdiseasesapp.utils.ext.isEmailValid
import com.edwinyosua.fishdiseasesapp.utils.ext.toastyMsg
import es.dmoral.toasty.Toasty
import org.koin.android.ext.android.inject


class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val registerViewModel: RegisterViewModel by inject()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun initInsets(): Boolean = false

    override fun initKeyboardHandler(): Boolean = true

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        binding.apply {
            btnSignUp.setOnClickListener {

                val name = editFullname.text?.trim().toString()
                val email = editEmail.text?.trim().toString()
                val pass = editNewPass.text?.trim().toString()
                val passConfirm = editConfirmPass.text?.trim().toString()




                if (pass != passConfirm) toastyMsg(requireContext(), "Password Doesn't Match", 2)

                if (passConfirm.isEmpty()) toastyMsg(requireContext(), "Confirmation Password is Empty !", 2)

                if(pass.length < 8) toastyMsg(requireContext(), "Password is Less Than 8 Character", 2)

                if (pass.isEmpty()) toastyMsg(requireContext(), "Password is Empty !", 2)

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()) toastyMsg(requireContext(), "Wrong Email Format !", 2)

                if (email.isEmpty()) toastyMsg(requireContext(), "Email is Empty !", 2)

                if (name.isEmpty()) toastyMsg(requireContext(), "Name is Empty !", 2)

                when {
                    email.isEmailValid() && pass.length >= 8 && passConfirm == pass -> {
                        registerViewModel.register(name, email, pass)
                    }
                }


//                if (
//                    name.isNotEmpty() &&
//                    (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) &&
//                    (pass.isNotEmpty() && pass == passConfirm)
//                ) {
//                    registerViewModel.register(name, email, pass)
//                }
            }

            txvLinkLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {

        registerViewModel.registerResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResult.Success -> Toasty.success(
                    requireContext(),
                    "Register Success",
                    Toast.LENGTH_SHORT
                ).show()

                is ApiResult.Error -> Toasty.error(
                    requireContext(),
                    result.error,
                    Toast.LENGTH_SHORT
                ).show()

                ApiResult.Loading -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

}