package com.oss.gallery.feature_authorization.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.oss.gallery.R
import com.oss.gallery.databinding.ActivityAuthBinding
import com.oss.gallery.feature_authorization.presentation.login_fragment.LoginFragment
import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import com.oss.gallery.feature_posts.presentation.ErrorFragment
import com.oss.gallery.feature_posts.presentation.MainActivity
import com.oss.gallery.feature_posts.utils.collectOnLifecycle
import com.oss.gallery.feature_posts.utils.lazyUnsafe
import com.oss.gallery.feature_posts.utils.replaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity(), AuthNavigator {

    private val binding by lazyUnsafe {
        ActivityAuthBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<AuthViewModelImpl>()
    private var keepSplashOnScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        installSplashScreen().apply {

            setKeepOnScreenCondition { keepSplashOnScreen }

            Handler(Looper.getMainLooper()).postDelayed(
                { keepSplashOnScreen = false },
//                BuildConfig.SPLASH_DELAY
                800L
            )
        }

        setSupportActionBar(binding.Toolbar)
    }

    override fun onStart() {
        super.onStart()
        initListeners()

        supportActionBar?.title = getString(R.string.login_tv_entry_text)
    }

    private fun initListeners() {
        viewModel.tokenState.collectOnLifecycle(this) { token ->
            if (token.isNotEmpty()) {
                viewModel.checkTokenState()
            } else replaceFragment(LoginFragment(), addStack = false)
        }


        viewModel.authUiState.collectOnLifecycle(this) { authUiState ->
            when (authUiState) {
                is AuthUiStates.Success<*> -> changeActivity()
                is AuthUiStates.Empty -> Unit
                is AuthUiStates.Loading -> Unit
                is AuthUiStates.Error<*> -> replaceFragment(ErrorFragment(), addStack = false)
            }
        }
    }

    override fun changeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }
}