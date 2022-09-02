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
import com.oss.gallery.ui.activities.MainActivity
import com.oss.gallery.ui.login_fragment.LoginFragment
import com.oss.gallery.ui.states.TokenState
import com.oss.gallery.utils.collectOnLifecycle
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
                    token = tokenState.token
                    validateToken(token)
                }
                is TokenState.Empty -> {
                    token = tokenState.token
                    validateToken(token)
                }
            }
        }
    }

    private fun validateToken(token: String) {
        if (token.isNotEmpty()) {
            launchScreen()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .commit()
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