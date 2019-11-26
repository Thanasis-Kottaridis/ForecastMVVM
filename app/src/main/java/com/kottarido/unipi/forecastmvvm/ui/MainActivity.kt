package com.kottarido.unipi.forecastmvvm.ui

import android.accessibilityservice.AccessibilityButtonController
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.kottarido.unipi.forecastmvvm.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // dilonoume oti i supportActionBar gia auto to activity oti tha einai
        // i custom tollbar pou exoume ftia3i sto activity_main.xml
        setSupportActionBar(toolbar)

        // arxikopoioume ton navController kanontas findNavController apo auto to activity
        // kai me viewID na einai to nav_host_fragment
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        // kanoume setUp to bottom navigation menu me auton ton navController
        // o opoios opote patame ena button apo to BottomNavigationMenu tha pernei auto to ID
        // kai tha kitaei gia auto to ID sto mobile navigation Graph
        // opote einai poli simantiko to onoma tou bottom menu item kai tou Fragment na einai idia
        bottom_nav.setupWithNavController(navController)

        // kanoume setUp tin actionBar me ton navControler
        NavigationUI.setupActionBarWithNavController(this,navController)

    }

    // edo kanoume override tin methodo pou tha kalite otan patame to back button pano asto toolbar
    // auto legete up navigation  H navigate up
    override fun onSupportNavigateUp(): Boolean {
        // to drawerLayout einai null gt den exoume DrawerLayout
        return NavigationUI.navigateUp(navController,null)
    }
}
