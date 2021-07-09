package com.parth.helloweatherapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parth.helloweatherapplication.R
import com.parth.helloweatherapplication.databinding.ActivityMainBinding
import com.parth.helloweatherapplication.ui.BaseActivity
import com.parth.helloweatherapplication.ui.auth.AuthActivity
import com.parth.helloweatherapplication.ui.main.account.BaseAccountFragment
import com.parth.helloweatherapplication.ui.main.account.ChangePasswordFragment
import com.parth.helloweatherapplication.ui.main.account.UpdateAccountFragment
import com.parth.helloweatherapplication.ui.main.blog.BaseBlogFragment
import com.parth.helloweatherapplication.ui.main.blog.UpdateBlogFragment
import com.parth.helloweatherapplication.ui.main.blog.ViewBlogFragment
import com.parth.helloweatherapplication.ui.main.create_blog.BaseCreateBlogFragment
import com.parth.helloweatherapplication.util.BottomNavController
import com.parth.helloweatherapplication.util.setUpNavigation

class MainActivity : BaseActivity(),
    BottomNavController.NavGraphProvider,
    BottomNavController.OnNavigationGraphChanged,
    BottomNavController.OnNavigationReselectedListener
{

    private lateinit var binding: ActivityMainBinding

    private lateinit var bottomNavigationView: BottomNavigationView

    private val bottomNavController by lazy(LazyThreadSafetyMode.NONE) {
        BottomNavController(
            this,
            R.id.main_nav_host_fragment,
            R.id.nav_blog,
            this,
            this)
    }

    override fun getNavGraphId(itemId: Int) = when(itemId){
        R.id.nav_blog -> {
            R.navigation.nav_blog
        }
        R.id.nav_create_blog -> {
            R.navigation.nav_create_blog
        }
        R.id.nav_account -> {
            R.navigation.nav_account
        }
        else -> {
            R.navigation.nav_blog
        }
    }

    override fun onGraphChange() {
        cancelActiveJobs()
        expandAppBar()
    }

    private fun cancelActiveJobs(){
        val fragments = bottomNavController.fragmentManager
            .findFragmentById(bottomNavController.containerId)
            ?.childFragmentManager
            ?.fragments
        if(fragments != null){
            for(fragment in fragments){
                (fragment as? BaseAccountFragment)?.cancelActiveJobs()
                if(fragment is BaseBlogFragment){
                    fragment.cancelActiveJobs()
                }
                if(fragment is BaseCreateBlogFragment){
                    fragment.cancelActiveJobs()
                }
            }
        }
        displayProgressBar(false)
    }

    override fun onReselectNavItem(
        navController: NavController,
        fragment: Fragment
    ) = when(fragment){

        is ViewBlogFragment -> {
            navController.navigate(R.id.action_viewBlogFragment_to_home)
        }

        is UpdateBlogFragment -> {
            navController.navigate(R.id.action_updateBlogFragment_to_home)
        }

        is UpdateAccountFragment -> {
            navController.navigate(R.id.action_updateAccountFragment_to_home)
        }

        is ChangePasswordFragment -> {
            navController.navigate(R.id.action_changePasswordFragment_to_home)
        }

        else -> {
            // do nothing
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun expandAppBar() {
        findViewById<AppBarLayout>(R.id.app_bar).setExpanded(true)
    }

    override fun onBackPressed() = bottomNavController.onBackPressed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupActionBar()
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setUpNavigation(bottomNavController, this)
        if (savedInstanceState == null) {
            bottomNavController.onNavigationItemSelected()
        }

        subscribeObservers()
    }

    private fun setupActionBar(){
        setSupportActionBar(binding.toolBar)
    }

    fun subscribeObservers(){
        sessionManager.cachedToken.observe(this, Observer{ authToken ->
            Log.d("Main Activity", "MainActivity, subscribeObservers: ViewState: ${authToken}")
            if(authToken == null || authToken.account_pk == -1 || authToken.token == null){
                navAuthActivity()
                finish()
            }
        })
    }

    private fun navAuthActivity(){
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun displayProgressBar(bool: Boolean){
        if(bool){
            binding.progressBar.visibility = View.VISIBLE
        }
        else{
            binding.progressBar.visibility = View.GONE
        }
    }
}