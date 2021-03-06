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
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amora.myseasonalanime.R
import com.amora.myseasonalanime.databinding.FragmentDetailAnimeBinding
import com.amora.myseasonalanime.di.Injection
import com.amora.myseasonalanime.utils.appToast
import com.amora.myseasonalanime.utils.gone
import com.amora.myseasonalanime.views.features.detail.characters.CharactersAdapter
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
            CharactersAdapter(CharactersAdapter.CharactersListener { charId -> showDetailChar(charId) })
        val trailerAdapters =
            TrailerAdapter(TrailerAdapter.TrailerListener { url -> showTrailer(url) })
        val viewModelFactory = Injection.provideViewModelFactory(requireContext(), this)

        viewModel =
            ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java].apply {
                setDetailAnime(id)
                detailAnime.observe(viewLifecycleOwner) { anime ->
                    binding.anime = anime

                    binding.apply {
                        if (trailerRv.isVisible || charactersItemRv.isVisible) {
                            progressBar.gone()
                        }
                    }

                    anime.genres.let {
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
                                text = anime.genres[genres].name
                            }

                            // Set the list of genres while less than Api size and for not make the list double
                            binding.apply {
                                if (listGenres.size < anime.genres.size) {
                                    listGenres.addView(genreTextView)
                                }
                            }
                        }
                    }
                }

                with(binding) {
                    charactersItemRv.adapter = charactersAdapter
                    trailerRv.adapter = trailerAdapters

                    /*fab.setOnClickListener { view ->
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }*/
                }
            }
    }

    private fun showDetailChar(charId: Int) {
        this.findNavController()
            .navigate(DetailFragmentDirections.actionDetailFragmentToDetailCharaFragment(charId))
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