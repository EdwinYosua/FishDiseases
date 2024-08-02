package com.edwinyosua.fishdiseasesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.R
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by inject()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initInsets(): Boolean = true

    override fun initKeyboardHandler(): Boolean = false

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        binding.apply {
            btnAnalyze.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_resultFragment)
            }

            btnImgLogout.setOnClickListener {
                homeViewModel.logout()
                //add textbox for logout confirmation later
                Toast.makeText(requireContext(), "LOGOUT SUKSES", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_homeFragment_to_onBoardingFragment)
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}


}