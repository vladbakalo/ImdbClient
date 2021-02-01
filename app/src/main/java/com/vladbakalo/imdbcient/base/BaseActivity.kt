package com.vladbakalo.imdbcient.base

import androidx.navigation.NavController
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity: DaggerAppCompatActivity() {

    abstract fun getNavController(): NavController

    fun isCurrentNavHasBackStack(): Boolean {
        return getNavController().previousBackStackEntry != null
    }
}