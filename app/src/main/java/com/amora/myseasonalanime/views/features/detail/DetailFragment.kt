package com.amora.myseasonalanime.views.features.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.amora.myseasonalanime.databinding.FragmentDetailAnimeBinding
import com.amora.myseasonalanime.utils.appToast
import com.amora.myseasonalanime.views.base.viewmodel.ViewModelFactory

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailAnimeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailAnimeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner

        setupLayout()
    }

    private fun setupLayout() {
        TODO("Not yet implemented")
    }

    private fun showTrailer(url: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        try {
            startActivity(intent)
        } catch (t: Throwable) {
            appToast("Ups, slowly!")
        }
    }
}