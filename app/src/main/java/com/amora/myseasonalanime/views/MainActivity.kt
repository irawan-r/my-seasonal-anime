package com.amora.myseasonalanime.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.amora.myseasonalanime.R
import com.amora.myseasonalanime.databinding.ActivityMainBinding
import com.amora.myseasonalanime.utils.gone
import com.amora.myseasonalanime.utils.visible
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val windowsInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView)

        windowsInsetsController?.isAppearanceLightNavigationBars = true

        val navView: BottomNavigationView = binding.navView
        setSupportActionBar(findViewById(R.id.main_toolbar))
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.updatePadding(insets.left, insets.top, insets.right, insets.bottom)

            WindowInsetsCompat.CONSUMED
        }

        // Custom bottom navigation based on destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.popularAnimeFragment ||
                destination.id == R.id.homeFragment ||
                destination.id == R.id.feedFragment
            ) {
                navView.visible()
            } else {
                navView.gone()
            }
        }

        // Setting of what inside the bottom navigation
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.popularAnimeFragment, R.id.homeFragment, R.id.feedFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}