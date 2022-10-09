package com.oss.gallery.feature_authorization.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View.OnClickListener
import androidx.constraintlayout.widget.ConstraintLayout
import com.oss.gallery.R
import com.oss.gallery.databinding.LoadingButtonBinding

class LoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {

    private val binding = LoadingButtonBinding.inflate(LayoutInflater.from(context), this)

    private val mListener = OnClickListener {
        loading = !loading
    }

    var loading: Boolean = false
        set(value) {
            field = value
            with(binding) {
                if (value) {
                    progressBar.visibility = VISIBLE
                    button.visibility = GONE
                } else {
                    progressBar.visibility = GONE
                    button.visibility = VISIBLE
                }
            }
        }

    init {
        initAttributes(attrs)
    }

    private fun initAttributes(
        attrs: AttributeSet?
    ) {
        if (attrs == null) return
        context.obtainStyledAttributes(attrs, R.styleable.LoadingButton).apply {
            val buttonText = getString(R.styleable.LoadingButton_lb_text).orEmpty()
            binding.button.text = buttonText

            recycle()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_UP) {
            mListener.onClick(this)
        }
        return super.dispatchTouchEvent(ev)
    }
}