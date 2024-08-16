package com.edwinyosua.fishdiseasesapp.presentation.home

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.edwinyosua.fishdiseasesapp.R
import com.edwinyosua.fishdiseasesapp.base.BaseFragment
import com.edwinyosua.fishdiseasesapp.data.local.SettingPreference
import com.edwinyosua.fishdiseasesapp.databinding.FragmentHomeBinding
import com.edwinyosua.fishdiseasesapp.utils.Gallery
import com.edwinyosua.fishdiseasesapp.utils.ext.dialogFragment
import com.edwinyosua.fishdiseasesapp.utils.toastyMsg
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val pref: SettingPreference by inject()
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
                dialogFragment(
                    title = "Logout",
                    positiveBttnTxt = "Yes",
                    negativeBttnTxt = "No",
                    onPositiveClick = {
                        lifecycleScope.launch { pref.clearUserLoginData() }
//                        Toasty.success(requireContext(), "You are logged out !", Toast.LENGTH_SHORT)
//                            .show()
                        toastyMsg(requireContext(), "You are logged out !", Toast.LENGTH_SHORT)
                        findNavController().navigate(R.id.action_homeFragment_to_onBoardingFragment)
                    }
                )
            }

            btnGallery.setOnClickListener {
                Gallery.launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }

    override fun initProcess() {

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

    override fun initObservers() {}


}