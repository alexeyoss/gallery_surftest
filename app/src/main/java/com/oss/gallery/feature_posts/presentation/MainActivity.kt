package com.oss.gallery.feature_posts.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.oss.gallery.R
import com.oss.gallery.databinding.ActivityMainBinding
import com.oss.gallery.feature_authorization.presentation.AuthActivity
import com.oss.gallery.feature_posts.presentation.main_fragment.MainFragment
import com.oss.gallery.feature_posts.presentation.postdetails_fragment.PostDetailsFragment
import com.oss.gallery.feature_posts.presentation.profile_fragment.ProfileFragment
import com.oss.gallery.feature_posts.presentation.utils.MainNavigator
import com.oss.gallery.feature_posts.presentation.utils.ToolbarHandler
import com.oss.gallery.feature_posts.utils.lazyUnsafe
import com.oss.gallery.feature_posts.utils.replaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainNavigator {
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val binding by lazyUnsafe {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navController by lazy {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navHost.navController
    }

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentViewCreated(
            fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateToolbarTitle()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.Toolbar)

        /**
         * https://issuetracker.google.com/issues/142847973?pli=1
         * **/
//        val navController = findNavController(R.id.fragmentContainer)
        val navView = binding.bottomNavigationView

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    private fun updateToolbarTitle() = with(binding) {
        val fragment = currentFragment

        if (fragment is ToolbarHandler) {
            supportActionBar?.title = getString(fragment.getStringRes())
        } else {
            supportActionBar?.title = getString(R.string.app_name)
        }
    }


    override fun changeFragment(fragment: Fragment?) {
        when (fragment) {
            is ProfileFragment -> startActivity(Intent(this, AuthActivity::class.java))
            is MainFragment -> replaceFragment(PostDetailsFragment(), addStack = true)
        }
    }

    override fun hideBottomNavigation() {
        TODO("Not yet implemented")
    }

    override fun hideOptionsMenu() {
        TODO("Not yet implemented")
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }
}