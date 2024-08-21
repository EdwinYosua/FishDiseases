package com.edwinyosua.fishdiseasesapp.presentation.home

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.R
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.data.local.SettingPreference
import com.edwinyosua.fishdiseasesapp.data.network.ApiResult
import com.edwinyosua.fishdiseasesapp.databinding.FragmentHomeBinding
import com.edwinyosua.fishdiseasesapp.utils.Gallery
import com.edwinyosua.fishdiseasesapp.utils.Gallery.imgFile
import com.edwinyosua.fishdiseasesapp.utils.ext.dialogFragment
import com.edwinyosua.fishdiseasesapp.utils.toastyMsg
import com.edwinyosua.fishdiseasesapp.utils.uriToFile
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.io.File

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val pref: SettingPreference by inject()
    private val homeViewModel: HomeViewModel by inject()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initInsets(): Boolean = true

    override fun initKeyboardHandler(): Boolean = false

    override fun initIntent() {}

    override fun initUI() {
        binding.apply {
            //pick image from gallery
            Gallery.launcherGallery = registerForActivityResult(
                ActivityResultContracts.PickVisualMedia()
            ) { uri: Uri? ->
                if (uri != null) {
                    Gallery.currentImgUri = uri


                    //set image in image preview
                    Gallery.currentImgUri.let {
                        Log.d("Image URI", "ShowImg: $it")
                        previewImage.setImageURI(it)
                    }
                } else {
                    Log.d("Photo Picker", "No Media Selected")
                }
            }
        }
    }

    override fun initAction() {
        binding.apply {
            btnAnalyze.setOnClickListener {
                if (Gallery.currentImgUri != null) {
                    homeViewModel.analyzeImage(startUploadImage())
                    Gallery.currentImgUri = null
                } else toastyMsg(requireContext(), "No Media Selected", 0)
            }

            btnImgLogout.setOnClickListener {
                dialogFragment(
                    title = "Logout",
                    positiveBttnTxt = "Yes",
                    negativeBttnTxt = "No",
                    onPositiveClick = {
                        lifecycleScope.launch { pref.clearUserLoginData() }
                        toastyMsg(requireContext(), "You are logged out !", 1)
                        findNavController().navigate(R.id.action_homeFragment_to_onBoardingFragment)
                    }
                )
            }

            btnGallery.setOnClickListener {
                Gallery.launcherGallery?.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {

        homeViewModel.modelResult.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiResult.Success -> {

                    disableButton(false)
                    binding.progBar.hide()

                    val navigateWithResult =
                        HomeFragmentDirections.actionHomeFragmentToResultFragment(response.data.prediction)
                    findNavController().navigate(navigateWithResult)

                    toastyMsg(requireContext(), "Analyze Success !", 1)

                    homeViewModel.setToNull()

                    Log.d("HomeFragment", response.data.prediction)
                }

                is ApiResult.Error -> {
                    binding.progBar.hide()
                    toastyMsg(requireContext(), response.error, 0)
                    disableButton(false)
                    Log.d("HomeFragment", response.error)
                }

                ApiResult.Loading -> {
//                    toastyMsg(requireContext(), "Please Wait", 2)
                    binding.apply {
                        progBar.show()
                        disableButton(true)
                    }
                }

                null -> {}
            }
        }
    }

    private fun disableButton(isDisabled: Boolean) {
        binding.apply {
            if (isDisabled) {
                btnGallery.isEnabled = false
                btnAnalyze.isEnabled = false
                btnImgLogout.isEnabled = false
                btnCamera.isEnabled = false
                btnImgHistory.isEnabled = false
            } else {
                btnGallery.isEnabled = true
                btnAnalyze.isEnabled = true
                btnImgLogout.isEnabled = true
                btnCamera.isEnabled = true
                btnImgHistory.isEnabled = true
            }
        }
    }

    private fun startUploadImage(): File {
        Gallery.currentImgUri?.let { uri ->
            imgFile = uriToFile(uri, requireContext())
            Log.d("Img", "Show Image ${imgFile.path}")
        }
        return imgFile
    }


}
