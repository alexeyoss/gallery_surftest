package com.oss.gallery.feature_posts.presentation.main_fragment.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PostHorizontalDividerItemDecoration(
    private val divider: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val onSideHorizontalDivider = divider / 2

        with(outRect) {
            left = onSideHorizontalDivider
            right = onSideHorizontalDivider
        }
    }
}