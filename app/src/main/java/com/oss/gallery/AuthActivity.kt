package com.oss.gallery

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.oss.gallery.contract.Navigator
import com.oss.gallery.databinding.ActivityAuthBinding
import com.oss.gallery.ui.login_screen.LoginFragment
import com.oss.gallery.utils.Constants

class AuthActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityAuthBinding
    private var keepSplashOnScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            // TODO Check current user logging state while 500L not gone
            setKeepOnScreenCondition { keepSplashOnScreen }
            Handler(Looper.getMainLooper()).postDelayed(
                { keepSplashOnScreen = false },
                Constants.SPLASH_DELAY
            )
        }
        binding = ActivityAuthBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setSupportActionBar(binding.authToolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

//        if (user.authed) { TODO
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, LoginFragment())
            .commit()
//        }

    }

    override fun launchScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun goBack() {

    }

    override fun hideBottomNavigation() {

    }
}