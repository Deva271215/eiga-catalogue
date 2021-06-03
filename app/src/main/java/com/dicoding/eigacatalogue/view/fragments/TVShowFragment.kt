package com.dicoding.eigacatalogue.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.eigacatalogue.ViewModelFactory
import com.dicoding.eigacatalogue.databinding.FragmentTVShowBinding
import com.dicoding.eigacatalogue.view.adapters.TVShowAdapter
import com.dicoding.eigacatalogue.viewmodels.TVShowViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [TVShowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TVShowFragment : Fragment() {

    companion object {
        private const val TAG = "TVShowFragment"
    }

    private lateinit var tvsfBinding: FragmentTVShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tvsfBinding = FragmentTVShowBinding.inflate(layoutInflater, container, false)
        return tvsfBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null){
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]
            val tvAdapter = TVShowAdapter()

            viewModel.getTVShow().observe(viewLifecycleOwner, {
                tvAdapter.setTVShow(it)
                tvAdapter.notifyDataSetChanged()
            })

            with(tvsfBinding.fragmentTvshow){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }
}