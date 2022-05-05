package com.amora.myseasonalanime.views

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import com.amora.myseasonalanime.R
import com.amora.myseasonalanime.databinding.ActivityDetailBinding

class DetailActivity: AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.main_toolbar))
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.)
    }
}