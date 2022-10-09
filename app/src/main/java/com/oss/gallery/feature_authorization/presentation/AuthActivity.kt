package com.oss.gallery.feature_authorization.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.oss.gallery.BuildConfig
import com.oss.gallery.R
import com.oss.gallery.databinding.ActivityAuthBinding
import com.oss.gallery.feature_authorization.presentation.login_fragment.LoginFragment
import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import com.oss.gallery.feature_posts.contract.AuthNavigator
import com.oss.gallery.feature_posts.presentation.MainActivity
import com.oss.gallery.feature_posts.utils.collectOnLifecycle
import com.oss.gallery.feature_posts.utils.replaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity(), AuthNavigator {

    private val binding by lazy { ActivityAuthBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<AuthViewModelImpl>()
    private var keepSplashOnScreen = true

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        installSplashScreen().apply {

            setKeepOnScreenCondition { keepSplashOnScreen }

            Handler(Looper.getMainLooper()).postDelayed(
                { keepSplashOnScreen = false },
                BuildConfig.SPLASH_DELAY // TODO guess the splash delay is not working
            )
        }

        setSupportActionBar(binding.Toolbar)
    }

    override fun onStart() {
        super.onStart()
        initListeners()

        supportActionBar!!.title = getString(R.string.login_tv_entry_text)
    }

    private fun initListeners() {
        viewModel.tokenState.collectOnLifecycle(this) { token ->
            if (token.isNotEmpty()) {
                viewModel.checkTokenState()
            } else replaceFragment(LoginFragment(), addStack = false)
        }


        viewModel.authUiState.collectOnLifecycle(this)
        { authUiState ->
            when (authUiState) {
                is AuthUiStates.Success<*> -> changeActivity(null)
                is AuthUiStates.Empty -> Unit
                is AuthUiStates.Loading -> Unit
                is AuthUiStates.Error<*> -> replaceFragment(LoginFragment(), addStack = false)
            }
        }
    }

    override fun fragmentIsClickable(enable: Boolean) = with(binding) {
        when (currentFragment) {
            // TODO not working
            is LoginFragment -> fragmentContainer.isClickable = enable
        }
    }

    override fun changeActivity(fragment: Fragment?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }
}