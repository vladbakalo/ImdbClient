package com.vladbakalo.imdbcient

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vladbakalo.imdbcient.base.BaseActivity
import com.vladbakalo.imdbcient.databinding.MainActivityBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        setSupportActionBar(binding.mainToolbar)
    }

    override fun getNavController(): NavController{
        return binding.mainFcvContainer.findNavController()
    }

    override fun onBackPressed() {
        if (isCurrentNavHasBackStack()){
            getNavController().popBackStack()
            return
        }
        super.onBackPressed()
    }
}