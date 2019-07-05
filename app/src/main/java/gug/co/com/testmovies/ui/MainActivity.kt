package gug.co.com.testmovies.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import gug.co.com.testmovies.R
import gug.co.com.testmovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding
    lateinit var host: NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.moviesPopular, R.id.moviesTopRated, R.id.moviesUpComing),
            null
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bottomNavView.setupWithNavController(navController)

        setOnDestinationChangedListener()

    }

    private fun setOnDestinationChangedListener() {

        host.navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.movieDetailFragment -> {
                    binding.bottomNavView.visibility = View.GONE
                    binding.toolbar.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavView.visibility = View.VISIBLE
                    binding.toolbar.visibility = View.VISIBLE
                }
            }

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return host.navController.navigateUp(appBarConfiguration)
    }
}
