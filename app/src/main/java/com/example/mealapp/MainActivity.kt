package com.example.mealapp

import android.app.ActionBar.LayoutParams
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.MenuItem
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mealapp.databinding.ActivityMainBinding
import com.example.mealapp.fragments.FiltersFragment
import com.example.mealapp.fragments.MealsFragment
import com.example.mealapp.viewmodel.MainViewModel
import com.example.mealapp.viewmodel.MainViewModelFactory
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mainViewModel : MainViewModel

    private lateinit var toggle : ActionBarDrawerToggle

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialising ViewModel
        mainViewModel = ViewModelProvider(this,
            MainViewModelFactory(application))[MainViewModel::class.java]


//        setUpDrawer()


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)


        // Setting up observe on App title Live data in View Model

//        mainViewModel.appTitle.observe(this,
//            Observer {
//                title = it
//            })
        setUpActionBar()
        setupBottomNavigationMenu()
    }


    private fun setUpActionBar(){
        val bar : ActionBar? = supportActionBar
        if(bar!=null){
            val tv : TextView = TextView(applicationContext);
            val lp : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, // Width of TextView
                LayoutParams.WRAP_CONTENT); // Height of TextView
            tv.layoutParams = lp;
            // Setting up observe on App title Live data in View Model
            mainViewModel.appTitle.observe(this,
                Observer {
                    tv.text = it
                })
            tv.gravity = Gravity.NO_GRAVITY;
            tv.setTextColor(Color.WHITE);
            tv.isSingleLine = true
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F);
            bar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
            bar.customView = tv;

        }
    }

//    private fun setUpDrawer(){
//
//        toggle = ActionBarDrawerToggle(
//            this@MainActivity,
//            binding.drawer,
//            R.string.open,
//            R.string.close
//        )
//
//        binding.drawer.addDrawerListener(toggle)
//        toggle.syncState()
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
////        // functionality for the nav drawer items
////        val navHostFragment = supportFragmentManager
////            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
////        val navController = navHostFragment.navController
//
//        binding.navView.setupWithNavController(navController)
//
//        val navView : NavigationView = findViewById(R.id.navView)
//        navView.setNavigationItemSelectedListener {
//            it.isChecked = true
//            when(it.itemId){
//                R.id.mealsFragment ->
//                    replaceFragment(MealsFragment(), "Meals")
//                R.id.filtersFragment -> {
//                    Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
//                    replaceFragment(FiltersFragment(), "Filters")
//                }
//
//            }
//            true
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

//    private fun replaceFragment(fragment: Fragment, title : String) {
//        val fragmentManager = supportFragmentManager
//        val fragTrans = fragmentManager.beginTransaction()
////        fragTrans.replace(R.id.fragmentContainerView, fragment)
//        fragTrans.replace(R.id.fragmentContainerView, fragment)
//        fragTrans.commit()
//
//
//
//        binding.drawer.closeDrawers()
//        setTitle(title)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(toggle.onOptionsItemSelected(item)){
//            return true
//        }
//
//        return super.onOptionsItemSelected(item)
//    }


    private fun setupBottomNavigationMenu(){
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.categoriesFragment, R.id.filtersFragment))
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
//        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)
    }




}