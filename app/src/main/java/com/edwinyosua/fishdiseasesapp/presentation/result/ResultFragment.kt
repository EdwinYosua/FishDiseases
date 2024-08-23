package com.edwinyosua.fishdiseasesapp.presentation.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.R
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.databinding.FragmentResultBinding
import com.edwinyosua.fishdiseasesapp.utils.Gallery

class ResultFragment : BaseFragment<FragmentResultBinding>() {

    private var predictionResultName: String? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentResultBinding = FragmentResultBinding.inflate(layoutInflater, container, false)

    override fun initInsets(): Boolean = true

    override fun initKeyboardHandler(): Boolean = false

    override fun initIntent() {
        predictionResultName = arguments?.getString("predictionResultName")
    }

    override fun initUI() {
        binding.apply {
            previewImg.setImageURI(Gallery.currentImgUri)
            txvAnalyzeTitle.text = predictionResultName
        }
    }

    override fun initAction() {
        binding.btnClose.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }
    }

    override fun initProcess() {
        Gallery.currentImgUri = null
    }

    override fun initObservers() {}

}