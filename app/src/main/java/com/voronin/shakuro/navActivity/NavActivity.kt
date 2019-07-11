package com.voronin.shakuro.navActivity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.voronin.shakuro.R
import com.voronin.shakuro.app.App
import com.voronin.shakuro.navActivity.viewModel.NavViewModel
import com.voronin.shakuro.utils.KodeinViewModelFactory
import com.voronin.shakuro.utils.getColoredVectorDrawable
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun viewModelInit() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NavViewModel::class.java)
        viewModel.onScreenChange = { navController.navigate(it.screenId, it.params) }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.contributorListScreen) {
                showBackBtn()
            } else {
                hideBackBtn()
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.contributors)
    }

    private fun hideBackBtn() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun showBackBtn() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.navigationIcon = getColoredVectorDrawable(this, R.drawable.ic_arrow_back_24dp, Color.WHITE)
    }
}

class ScreenInfo(val screenId: Int, val params: Bundle? = null)
