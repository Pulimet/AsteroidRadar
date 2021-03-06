package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.collectIt
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.ui.main.recycler.AsteroidAdapter
import com.udacity.asteroidradar.ui.main.recycler.AsteroidListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), MenuProvider, AsteroidListener {

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
        setRecyclerView()
        observeViewModel()
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setRecyclerView() {
        binding.asteroidRecycler.adapter = AsteroidAdapter(this)
    }

    private fun observeViewModel() {
        viewModel.navigate.collectIt(viewLifecycleOwner) { findNavController().navigate(it) }
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

    // AsteroidListener
    override fun onClick(asteroid: Asteroid) {
        viewModel.onAsteroidClick(asteroid)
    }
}
