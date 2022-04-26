package com.amora.myseasonalanime.views.features.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.amora.myseasonalanime.R
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.databinding.CharactersDialogBinding
import com.amora.myseasonalanime.databinding.FragmentDetailAnimeBinding
import com.amora.myseasonalanime.utils.appToast
import com.amora.myseasonalanime.views.base.viewmodel.ViewModelFactory
import com.amora.myseasonalanime.views.features.detail.characters.CharactersAdapter
import com.amora.myseasonalanime.views.features.detail.characters.detail.VoiceActorAdapter
import com.amora.myseasonalanime.views.features.detail.trailer.TrailerAdapter

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
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun setupLayout() {
        val id = DetailFragmentArgs.fromBundle(requireArguments()).id
        val charactersAdapter =
            CharactersAdapter(CharactersAdapter.CharactersListener { charId ->
                showDetailCharacter(charId)
            })
        val trailerAdapters =
            TrailerAdapter(TrailerAdapter.TrailerListener { url -> showTrailer(url) })
        val viewModelFactory = ViewModelFactory.getInstance()

        with(binding) {
            charactersItemRv.adapter = charactersAdapter
            trailerRv.adapter = trailerAdapters
        }

        viewModel =
            ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java].apply {
                setDetailAnime(id)
                detailAnime.observe(viewLifecycleOwner) { anime ->
                    binding.anime = anime
                    anime?.genres.let {
                        if (it != null) {
                            for (genres in it.indices) {
                                val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                                )
                                params.setMargins(0, 0, 20, 0)
                                val genreTextView = TextView(requireContext()).apply {
                                    setBackgroundResource(R.drawable.bg_genres)
                                    layoutParams = params
                                    setTextColor(Color.parseColor("#ffffff"))
                                    if (anime != null) {
                                        text = anime.genres[genres].name
                                    }
                                }
                                binding.listGenres.addView(genreTextView)
                            }
                        }
                    }
                }
            }
    }

    private fun showDetailCharacter(id: Int) {
        val mDialogView: CharactersDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.characters_dialog,
            null,
            false
        )
        val voiceActorAdapter = VoiceActorAdapter()
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