package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.collectIt
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        setDataBinding()
        return binding.root
    }

    private fun setDataBinding() {
        binding = FragmentMainBinding.inflate(layoutInflater).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        viewModel.imageUrl.collectIt(viewLifecycleOwner) {
            it?.let { Picasso.get().load(it).into(binding.activityMainImageOfTheDay) }
        }
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    // MenuProvider
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_overflow_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.show_all_menu -> {}
            R.id.show_buy_menu -> {}
            R.id.show_rent_menu -> {}
            else -> return false
        }
        return true
    }
}
