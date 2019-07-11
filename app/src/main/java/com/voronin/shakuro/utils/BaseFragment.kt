package com.voronin.shakuro.utils

import androidx.fragment.app.Fragment
import com.voronin.shakuro.app.App
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

/**
 * Base Fragment for all screens with kodein injection
 */
abstract class BaseFragment : Fragment(), KodeinAware {

    override val kodein: Kodein = App.kodein

    val viewModelFactory: KodeinViewModelFactory = KodeinViewModelFactory(kodein)

}