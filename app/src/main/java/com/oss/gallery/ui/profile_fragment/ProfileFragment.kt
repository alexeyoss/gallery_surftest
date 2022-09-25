package com.oss.gallery.ui.profile_fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.oss.gallery.R
import com.oss.gallery.contract.navigator
import com.oss.gallery.databinding.FragmentProfileBinding
import com.oss.gallery.ui.AlertDialogBuilder
import com.oss.gallery.ui.base_fragments.BaseMainFragments
import com.oss.gallery.ui.states.main_activity_states.MainUiStates
import com.oss.gallery.utils.collectOnLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseMainFragments(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModelImpl by viewModels()

    private lateinit var alertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        initListeners()
        initViews()

        return binding.root
    }

    fun initListeners() = with(binding) {
        logoutBtn.setOnClickListener {
            alertDialog.show()
        }

        viewModel.logoutFlow.collectOnLifecycle(this@ProfileFragment) { mainUIState ->
            when (mainUIState) {
                is MainUiStates.Empty -> logoutBtn.loading = false
                is MainUiStates.Success<*> -> {
                    viewModel.cleanStorageResources()
                    // TODO clarify maybe we need to delete the all files from Storage
                    navigator().changeActivity(this@ProfileFragment)
                }
                is MainUiStates.Error<*> -> {
                    logoutBtn.loading = false
                    // TODO show SnackBar
                }
                is MainUiStates.Loading -> {
                    logoutBtn.loading = true
                }
            }
        }

//        viewModel.cleanStorageResourcesFlow.collectOnLifecycle(this@ProfileFragment) { mainUIState ->
//            when (mainUIState) {
//                is MainUiStates.Empty -> Unit
//                is MainUiStates.Success<*> -> {
//                    binding.logoutBtn.loading = false
//                    navigator().changeActivity()
//                }
//                is MainUiStates.Error<*> -> {
//                    binding.logoutBtn.loading = false
//                    // TODO showsnackBar
//                }
//                is MainUiStates.Loading -> {
//                    binding.logoutBtn.loading = true
//                }
//            }
//        }

    }

    private fun initViews() {
        alertDialog =
            AlertDialogBuilder.createExitDialog(
                context = requireContext(),
                message = R.string.exit_text,
                onSuccess = {
                    viewModel.logout()
                } // TODO check how to init the side effects
            )
    }
}