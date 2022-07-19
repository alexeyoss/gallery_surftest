package com.oss.gallery.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.oss.gallery.R
import com.oss.gallery.databinding.LoadingButtonBinding

class LoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {

    private val binding = LoadingButtonBinding.inflate(LayoutInflater.from(context), this)

    var buttonText: String = ""
        set(value) {
            field = value
            binding.button.text = value
        }

    var loading: Boolean = false
        set(value) {
            field = value
            binding.progressBar.isVisible = value
        }

    init {
        context.withStyledAttributes(attrs, R.styleable.LoadingButton) {
            buttonText = getString(R.styleable.LoadingButton_lb_text) ?: ""
            loading = getBoolean(R.styleable.LoadingButton_lb_loading, false)
        }
    }
}