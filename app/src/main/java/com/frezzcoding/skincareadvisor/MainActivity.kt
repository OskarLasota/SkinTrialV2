package com.frezzcoding.skincareadvisor

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.frezzcoding.skincareadvisor.data.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private var user = User(0, false) //temporary logged in

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()
    }

    private fun setUI() {
        val navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
        bottom_nav.setOnItemReselectedListener {
            //do nothing to ignore reselection of same freagment
        }
        setupBottomNavMenu(navController)
        setSupportActionBar(toolbar)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accountFragment) {
            if (user.loggedIn) {
                Navigation.findNavController(findViewById(R.id.nav_host_fragment))
                    .navigate(R.id.accountFragment)
                return super.onOptionsItemSelected(item)
            } else {
                Navigation.findNavController(findViewById(R.id.nav_host_fragment))
                    .navigate(R.id.loginFragment)
                return super.onOptionsItemSelected(item)
            }

        }
        val navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
        val navigated = NavigationUI.onNavDestinationSelected(item, navController)
        return navigated || super.onOptionsItemSelected(item)
    }

}