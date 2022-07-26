package com.oss.gallery.activities

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
import com.oss.gallery.utils.Constants

class AuthActivity : AppCompatActivity(), AuthNavigator {

    private val binding by lazy { ActivityAuthBinding.inflate(layoutInflater) }
    private var keepSplashOnScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        installSplashScreen().apply {
            // TODO Check current user logging state while 500L not gone
            setKeepOnScreenCondition { keepSplashOnScreen }
            Handler(Looper.getMainLooper()).postDelayed(
                { keepSplashOnScreen = false },
                Constants.SPLASH_DELAY
            )
        }

        setSupportActionBar(binding.Toolbar)

//        if (user.authed) { TODO
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, LoginFragment())
            .commit()
//        }
    }

    override fun onStart() {
        super.onStart()
        supportActionBar!!.title = getString(R.string.login_tv_entry_text)
    }

    override fun launchScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun goBack() {
    }
}