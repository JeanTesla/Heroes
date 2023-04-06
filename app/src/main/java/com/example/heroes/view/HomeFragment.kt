package com.example.heroes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.heroes.MainActivity
import com.example.heroes.R
import com.example.heroes.adapter.ComicBookAdapter
import com.example.heroes.model.ComicResult
import com.example.heroes.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var listViewHeroes: ListView
    private val homeViewModel = HomeViewModel
    private val comicBookList = ArrayList<ComicResult>()
    private lateinit var comicBookListViewAdapter: ComicBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragment = inflater.inflate(R.layout.fragment_home, container, false)

        comicBookListViewAdapter = ComicBookAdapter(requireContext(), comicBookList)

        listViewHeroes = fragment.findViewById(R.id.list_view_comic_books)
        listViewHeroes.adapter = comicBookListViewAdapter

        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.comicBookList.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "Entrou aqui", Toast.LENGTH_LONG).show()
            comicBookList.addAll(it)
            //comicBookListAdapter.notifyDataSetChanged()
            comicBookListViewAdapter.notifyDataSetChanged()

        })

        homeViewModel.getAllComics()
    }

}