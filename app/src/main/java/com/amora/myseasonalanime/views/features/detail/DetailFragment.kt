package com.amora.myseasonalanime.views.features.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.detail.Trailer
import com.amora.myseasonalanime.databinding.FragmentDetailAnimeBinding
import com.amora.myseasonalanime.utils.appToast
import com.amora.myseasonalanime.views.adapter.CharactersAdapter
import com.amora.myseasonalanime.views.adapter.GenresAdapter
import com.amora.myseasonalanime.views.base.viewmodel.ViewModelFactory

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailAnimeBinding
    private lateinit var trailer: Trailer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
    }

    private fun setupLayout() {
        val id = DetailFragmentArgs.fromBundle(requireArguments()).id
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this,
            viewModelFactory)[DetailViewModel::class.java].apply {
            setDetailAnime(id)
            detailAnime.observe(viewLifecycleOwner) {
                binding.anime = it
            }
        }

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.posterTrailer.setOnClickListener { showTrailer(trailer.embedUrl) }

        binding.genresItemRv.adapter = GenresAdapter()
        binding.charactersItemRv.adapter = CharactersAdapter()
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