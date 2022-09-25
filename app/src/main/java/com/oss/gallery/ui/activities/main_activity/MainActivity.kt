package com.oss.gallery.ui.activities.main_activity

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
import com.oss.gallery.contract.MainNavigator
import com.oss.gallery.contract.ToolbarHandler
import com.oss.gallery.databinding.ActivityMainBinding
import com.oss.gallery.ui.activities.auth_activity.AuthActivity
import com.oss.gallery.ui.profile_fragment.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainNavigator {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.Toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        val navView = binding.bottomNavigationView
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.favoritesFragment,
                R.id.profileFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        // TODO the SearchBar implementation
//        val search = menu.findItem(R.id.app_bar_search)
//        val searchView = search.actionView as SearchView
//        searchView.isSubmitButtonEnabled = false
//        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    private fun updateUI() = with(binding) {
        val fragment = currentFragment

        if (fragment is ToolbarHandler) {
            supportActionBar?.title = getString(fragment.getStringRes())
        } else {
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    override fun changeActivity(fragment: Fragment?) {
        when (fragment) {
            is ProfileFragment -> {
                startActivity(
                    Intent(this, AuthActivity::class.java)
                )
            }
        }
    }

    override fun hideBottomNavigation() {
        TODO("Not yet implemented")
    }

    override fun hideOptionsMenu() {
        TODO("Not yet implemented")
    }

    override fun goBack() {
    }
}