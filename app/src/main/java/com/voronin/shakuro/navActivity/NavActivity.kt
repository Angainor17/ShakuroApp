package com.voronin.shakuro.navActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.voronin.shakuro.R
import com.voronin.shakuro.app.App
import com.voronin.shakuro.navActivity.viewModel.NavViewModel
import com.voronin.shakuro.utils.KodeinViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.generic.instance

class NavActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var viewModel: NavViewModel

    private val viewModelFactory: KodeinViewModelFactory by App.kodein.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        viewModelInit()
    }

    private fun viewModelInit() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NavViewModel::class.java)
        viewModel.screenLiveData.observe(this)
        {
            try {
                navController.navigate(it.screenId, it.params)
            } catch (e: Exception) {
                //need for library bug: Action throw exception on landscape mode change
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
    }
}

class ScreenInfo(val screenId: Int, val params: Bundle? = null)
