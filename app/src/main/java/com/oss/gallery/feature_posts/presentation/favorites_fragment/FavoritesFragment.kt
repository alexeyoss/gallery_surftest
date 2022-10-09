package com.oss.gallery.feature_posts.presentation.favorites_fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentFavoritesBinding
import com.oss.gallery.feature_posts.contract.ToolbarHandler
import com.oss.gallery.feature_posts.data.model.BasePictureModel
import com.oss.gallery.feature_posts.presentation.BaseMainFragments
import com.oss.gallery.feature_posts.utils.AlertDialogBuilder

class FavoritesFragment : BaseMainFragments(R.layout.fragment_favorites),
    FavoritesFragmentOnClickListener,
    ToolbarHandler {

    private lateinit var binding: FragmentFavoritesBinding
    private val mAdapter = FavoritesFragmentAdapter(this)

    private lateinit var alertDialog: AlertDialog

    // TODO Add custom filed "dateOfAddition" into the DataStorage cause we need to sort the list.
    //  The sort operation we can do in out request from the DataStorage
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        initViews()
        initListeners()
        initRecycleView()

        return binding.root
    }

    private fun initListeners() = with(binding) {

    }

    private fun initViews() {
        alertDialog = AlertDialogBuilder.createDialog(
            context = requireContext(),
            message = R.string.delete_post,
            onPositive = {
                // TODO build ViewModel
            },
            onNegative = {
                // TODO build ViewModel
            }
        )
    }

    private fun initRecycleView() = with(binding) {
        favoritesRv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    override fun onLikeClicked(basePictureModel: BasePictureModel) {
        alertDialog.show()
    }

    override fun getStringRes(): Int = R.string.favorites_title
}