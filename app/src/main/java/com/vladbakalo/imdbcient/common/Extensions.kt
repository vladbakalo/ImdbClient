package com.vladbakalo.imdbcient.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun ViewGroup.getLayoutInflater(): LayoutInflater {
    return this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}