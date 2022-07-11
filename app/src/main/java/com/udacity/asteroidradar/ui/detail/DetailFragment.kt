package com.udacity.asteroidradar.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        val binding = setDataBinding(inflater)
        return binding.root
    }

    private fun setDataBinding(inflater: LayoutInflater) = FragmentDetailBinding.inflate(inflater).also {
        it.lifecycleOwner = this
        it.asteroid = args.selectedAsteroid
        it.helpButton.setOnClickListener {
            displayAstronomicalUnitExplanationDialog()
        }
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}
