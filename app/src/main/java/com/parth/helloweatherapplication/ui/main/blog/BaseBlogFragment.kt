package com.parth.helloweatherapplication.ui.main.blog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.parth.helloweatherapplication.R
import com.parth.helloweatherapplication.ui.DataStateChangeListener
import dagger.android.support.DaggerFragment

abstract class BaseBlogFragment : DaggerFragment(){

    val TAG: String = "AppDebug"

    lateinit var stateChangeListener: DataStateChangeListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            stateChangeListener = context as DataStateChangeListener
        }catch(e: ClassCastException){
            Log.e(TAG, "$context must implement DataStateChangeListener" )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBarWithNavController(R.id.blogFragment, activity as AppCompatActivity)

        cancelActiveJobs()
    }

    fun cancelActiveJobs(){
//        viewModel.cancelActiveJobs()
    }

    /*
          @fragmentId is id of fragment from graph to be EXCLUDED from action back bar nav
        */
    fun setupActionBarWithNavController(fragmentId: Int, activity: AppCompatActivity){
        val appBarConfiguration = AppBarConfiguration(setOf(fragmentId))
        NavigationUI.setupActionBarWithNavController(
            activity,
            findNavController(),
            appBarConfiguration
        )
    }

}