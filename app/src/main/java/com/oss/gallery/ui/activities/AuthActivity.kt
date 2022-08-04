package com.oss.gallery.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.oss.gallery.R
import com.oss.gallery.contract.AuthNavigator
import com.oss.gallery.databinding.ActivityAuthBinding
import com.oss.gallery.ui.login_fragment.LoginFragment

class AuthActivity : AppCompatActivity(), AuthNavigator {

    private val binding by lazy { ActivityAuthBinding.inflate(layoutInflater) }
    private var keepSplashOnScreen = true
    private var loginStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        installSplashScreen().apply {
            // TODO Check current user logging state while 500L not gone
            setKeepOnScreenCondition { keepSplashOnScreen }

//            loginStatus = mViewModel.setEvent(Event.CheckAuth)

            Handler(Looper.getMainLooper()).postDelayed(
                { keepSplashOnScreen = false },
                SPLASH_DELAY
            )
        }

        if (loginStatus && savedInstanceState == null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .commit()
        }

        setSupportActionBar(binding.Toolbar)
    }

    override fun onStart() {
        super.onStart()
        supportActionBar!!.title = getString(R.string.login_tv_entry_text)
    }

    override fun launchScreen() {
        val intent = Intent(this, MainActivity::class.java) //TODO
        startActivity(intent)
    }

    override fun goBack() {
        onBackPressed()
    }

    companion object {
        const val SPLASH_DELAY = 500L
    }
}