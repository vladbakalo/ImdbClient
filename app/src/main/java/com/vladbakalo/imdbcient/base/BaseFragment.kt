package com.vladbakalo.imdbcient.base

import android.os.Bundle
import androidx.navigation.NavController
import dagger.android.support.DaggerFragment

abstract class BaseFragment<T :BaseViewModel>: DaggerFragment() {

    private var baseViewModel: T? = null

    val viewModel: T by lazy { baseViewModel!! }

    abstract fun provideViewModel(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewModel = if (baseViewModel == null) provideViewModel() else baseViewModel
    }

    fun getNavController(): NavController{
        val activity = activity as BaseActivity
        return activity.getNavController()
    }
}