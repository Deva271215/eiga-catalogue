package com.dicoding.eigacatalogue.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.eigacatalogue.DummyEntries
import com.dicoding.eigacatalogue.R
import com.dicoding.eigacatalogue.ViewModelFactory
import com.dicoding.eigacatalogue.databinding.FragmentMovieBinding
import com.dicoding.eigacatalogue.view.adapters.MovieAdapter
import com.dicoding.eigacatalogue.viewmodels.MovieViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {

    companion object {
        private const val TAG = "MovieFragment"
    }

    private lateinit var mfBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mfBinding = FragmentMovieBinding.inflate(
            layoutInflater,
            container,
            false)
        return mfBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            mfBinding.progressBar.visibility = View.VISIBLE

            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(
                this,
                factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            viewModel.getMovie().observe(viewLifecycleOwner, {
                movieAdapter.setMovie(it)
                movieAdapter.notifyDataSetChanged()
                mfBinding.progressBar.visibility = View.GONE
            })

            with(mfBinding.fragmentMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}