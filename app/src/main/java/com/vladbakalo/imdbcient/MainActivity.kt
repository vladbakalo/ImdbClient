package com.vladbakalo.imdbcient

import android.os.Bundle
import com.vladbakalo.imdbcient.base.BaseActivity
import com.vladbakalo.imdbcient.ui.main.MainFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}