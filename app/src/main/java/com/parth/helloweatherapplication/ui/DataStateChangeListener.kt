package com.parth.helloweatherapplication.ui


interface DataStateChangeListener{

    fun onDataStateChange(dataState: DataState<*>?)

    fun expandAppBar()
}