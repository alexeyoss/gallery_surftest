package com.oss.gallery.ui.activities.auth_activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.oss.gallery.BuildConfig
import com.oss.gallery.R
import com.oss.gallery.contract.AuthNavigator
import com.oss.gallery.databinding.ActivityAuthBinding
import com.oss.gallery.ui.activities.main_activity.MainActivity
import com.oss.gallery.ui.login_fragment.LoginFragment
import com.oss.gallery.ui.states.AuthUiStates
import com.oss.gallery.ui.states.TokenState
import com.oss.gallery.utils.collectOnLifecycle
import com.oss.gallery.utils.replaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity(), AuthNavigator {

    private val binding by lazy { ActivityAuthBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<AuthViewModelImpl>()
    private var keepSplashOnScreen = true
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        installSplashScreen().apply {

            setKeepOnScreenCondition { keepSplashOnScreen }

            viewModel.checkTokenStatus()

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
        viewModel.tokenState.collectOnLifecycle(this) { tokenState ->
            when (tokenState) {
                is TokenState.Exist -> {
                    if (tokenState.token.isNotEmpty()) {
                        viewModel.getPicturesFromNetwork("Token $token")
                    } else replaceFragment(LoginFragment(), addStack = false)
                }
                is TokenState.Empty -> Unit
            }
        }

        viewModel.authUiState.collectOnLifecycle(this) { authUiState ->
            when (authUiState) {
                is AuthUiStates.Success<*> -> launchScreen()
                is AuthUiStates.Empty -> Unit
                is AuthUiStates.Loading -> Unit
                is AuthUiStates.Error<*> -> replaceFragment(LoginFragment(), addStack = false)
            }
        }
    }

    override fun launchScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun goBack() {
        onBackPressed()
    }
}