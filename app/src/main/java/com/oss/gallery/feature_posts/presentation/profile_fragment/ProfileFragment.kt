package com.oss.gallery.feature_posts.presentation.profile_fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentProfileBinding
import com.oss.gallery.feature_posts.presentation.BaseMainFragments
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import com.oss.gallery.feature_posts.presentation.utils.navigator
import com.oss.gallery.feature_posts.utils.AlertDialogBuilder
import com.oss.gallery.feature_posts.utils.collectOnLifecycle
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()
        initViews()


        requireActivity().addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.setGroupVisible(R.id.search_id, false)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        })
    }

    private fun initListeners() = with(binding) {
        logoutBtn.setOnClickListener {
            alertDialog.show()
        }

        viewModel.logoutFlow.collectOnLifecycle(this@ProfileFragment) { mainUIState ->
            when (mainUIState) {
                is MainUiStates.Empty -> logoutBtn.loading = false
                is MainUiStates.Success<*> -> {
                    viewModel.cleanStorageResources()
                    // TODO clarify maybe we need to delete the all files from Storage
                    navigator().changeFragment(this@ProfileFragment)
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

        viewModel.cleanStorageResourcesFlow.collectOnLifecycle(this@ProfileFragment) { mainUIState ->
            when (mainUIState) {
                is MainUiStates.Empty -> Unit
                is MainUiStates.Success<*> -> {
                    binding.logoutBtn.loading = false
                    navigator().changeFragment(this@ProfileFragment)
                }
                is MainUiStates.Error<*> -> {
                    binding.logoutBtn.loading = false
                    // TODO showsnackBar
                }
                is MainUiStates.Loading -> {
                    binding.logoutBtn.loading = true
                }
            }
        }

    }

    private fun initViews() {
        alertDialog =
            AlertDialogBuilder.createDialog(
                context = requireContext(),
                message = R.string.exit_text,
                onPositive = {
                    viewModel.logout()
                },
                onNegative = {
                    binding.logoutBtn.loading = false
                }
            )
    }
}