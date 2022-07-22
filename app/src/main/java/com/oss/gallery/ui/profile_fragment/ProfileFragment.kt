package com.oss.gallery.ui.profile_fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentProfileBinding
import com.oss.gallery.ui.AlertDialogBuilder
import com.oss.gallery.ui.base_fragments.BaseMainFragments

class ProfileFragment : BaseMainFragments(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var alertListener: DialogInterface.OnClickListener
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

    private fun initListeners() = with(binding) {
        logoutBtn.setOnClickListener {
            alertDialog.show()
        }

        alertListener = DialogInterface.OnClickListener { _, button ->
            when (button) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Toast.makeText(
                        context,
                        "It works",
                        Toast.LENGTH_SHORT
                    ).show()
                    logoutBtn.loading = true
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    logoutBtn.loading = false
                }
            }
        }
    }

    private fun initViews() {
        alertDialog =
            AlertDialogBuilder.createDialog(
                context!!,
                " Вы точно хотите выйти из приложения?",
                alertListener
            )
    }
}