package com.example.heroes.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heroes.R
import com.example.heroes.adapter.ComicBookAdapter
import com.example.heroes.model.ComicResult
import com.example.heroes.model.QueryStringCreatorComicSearch
import com.example.heroes.view.components.DatePickerFragment
import com.example.heroes.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private val homeViewModel = HomeViewModel
    private val comicBookList = ArrayList<ComicResult>()
    private lateinit var comicBookRecycleViewAdapter: ComicBookAdapter

    private lateinit var editTextFindComicBook: EditText
    private lateinit var buttonFindComicBook: ImageButton
    private lateinit var editTextInitialDate: TextView
    private lateinit var editTextFinalDate: TextView
    private lateinit var buttonClearFilters: Button
    private lateinit var recycleView: RecyclerView

    private lateinit var textViewNotFound: TextView
    private lateinit var textViewNetworkError: TextView
    private lateinit var progressBar: ProgressBar

    private var queryStringSearch = QueryStringCreatorComicSearch()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragment = inflater.inflate(R.layout.fragment_home, container, false)

        editTextFindComicBook = fragment.findViewById(R.id.editTextFindComicBook)
        buttonFindComicBook = fragment.findViewById(R.id.buttonFindComicBook)
        editTextInitialDate = fragment.findViewById(R.id.editTextInitialDate)
        editTextFinalDate = fragment.findViewById(R.id.editTextFinalDate)
        buttonClearFilters = fragment.findViewById(R.id.buttonClearFilters)
        recycleView = fragment.findViewById(R.id.recycleViewComicBook)

        textViewNotFound = fragment.findViewById(R.id.textViewNotFound)
        textViewNetworkError = fragment.findViewById(R.id.textViewNetworkError)
        progressBar = fragment.findViewById(R.id.progressBar)

        val layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )

        recycleView.layoutManager = layoutManager

        comicBookRecycleViewAdapter = ComicBookAdapter(comicBookList, onClickListItem)

        val divider = DividerItemDecoration(
            context,
            layoutManager.orientation
        )
        divider.setDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.divider)!!)


        recycleView.addItemDecoration(divider)
        recycleView.adapter = comicBookRecycleViewAdapter

        buttonFindComicBook.setOnClickListener {
            showLoading()
            queryStringSearch.titleStartsWith = if (editTextFindComicBook.text.isBlank())
                null else editTextFindComicBook.text.toString()
            homeViewModel.getAllComics(queryStringSearch)
        }

        buttonClearFilters.setOnClickListener {
            clearFilters()
        }

        editTextInitialDate.setOnClickListener {
            DatePickerFragment("datePickerInitial", onSetDateSearchComicBook)
                .show(childFragmentManager, null)
        }

        editTextFinalDate.setOnClickListener {
            DatePickerFragment("datePickerFinal", onSetDateSearchComicBook)
                .show(childFragmentManager, null)
        }

        return fragment
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.comicBookList.observe(viewLifecycleOwner, Observer {
            comicBookList.clear()
            comicBookList.addAll(it)
            comicBookRecycleViewAdapter.notifyDataSetChanged()

            if (it.isNotEmpty()) showList() else showNotFound()
        })

        homeViewModel.comicRequestError.observe(viewLifecycleOwner, Observer {
            if (it) showNetworkError()
        })

        homeViewModel.getAllComics(queryStringSearch)
    }

    private val onSetDateSearchComicBook =
        { tag: String, brazilFormattedDate: String, americanFormattedDate: String ->
            when (tag) {
                "datePickerInitial" -> {
                    editTextInitialDate.text = brazilFormattedDate
                    queryStringSearch.initialDate = americanFormattedDate
                }
                "datePickerFinal" -> {
                    editTextFinalDate.text = brazilFormattedDate
                    queryStringSearch.finalDate = americanFormattedDate
                }
            }
        }

    private val onClickListItem = { item: ComicResult ->
        Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
//        childFragmentManager.commit {
//            add(R.id.fragmentContainerView, ComicBookDescriptionFragment())
//        }
    }

    fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recycleView.visibility = View.GONE
        textViewNotFound.visibility = View.GONE
        textViewNetworkError.visibility = View.GONE
    }

    fun showNotFound() {
        progressBar.visibility = View.GONE
        recycleView.visibility = View.GONE
        textViewNotFound.visibility = View.VISIBLE
        textViewNetworkError.visibility = View.GONE
    }

    fun showNetworkError() {
        progressBar.visibility = View.GONE
        recycleView.visibility = View.GONE
        textViewNotFound.visibility = View.GONE
        textViewNetworkError.visibility = View.VISIBLE
    }

    fun showList() {
        progressBar.visibility = View.GONE
        recycleView.visibility = View.VISIBLE
        textViewNotFound.visibility = View.GONE
        textViewNetworkError.visibility = View.GONE
    }

    fun clearFilters() {
        editTextFindComicBook.text.clear()
        editTextInitialDate.text = "Data Inicial"
        editTextFinalDate.text = "Data Final"
        queryStringSearch.clear()
    }
}