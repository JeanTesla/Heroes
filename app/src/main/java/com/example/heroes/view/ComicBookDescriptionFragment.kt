package com.example.heroes.view

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.heroes.R
import com.example.heroes.adapter.CarouselRVAdapter
import com.example.heroes.model.ComicCreators
import com.example.heroes.model.ComicResult
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ComicBookDescriptionFragment : Fragment() {

    private lateinit var selectedComicResultItem: ComicResult

    private lateinit var viewPager: ViewPager2
    private lateinit var imageViewComicBookDescription: ImageView
    private lateinit var textViewComicTitleDescription: TextView
    private lateinit var textViewComicYear: TextView
    private lateinit var textViewComicPages: TextView
    private lateinit var textViewCreators: TextView
    private lateinit var textViewDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_comic_book_description, container, false)

        viewPager = fragment.findViewById<ViewPager2?>(R.id.view_pager).apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
        }

        viewPager.adapter = CarouselRVAdapter(ArrayList(selectedComicResultItem.images))

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        viewPager.setPageTransformer(compositePageTransformer)

        imageViewComicBookDescription = fragment.findViewById(R.id.imageViewComicBookDescription)
        textViewComicTitleDescription = fragment.findViewById(R.id.textViewComicTitleDescription)
        textViewComicYear = fragment.findViewById(R.id.textViewComicYear)
        textViewComicPages = fragment.findViewById(R.id.textViewComicPages)
        textViewCreators = fragment.findViewById(R.id.textViewCreators)
        textViewDescription = fragment.findViewById(R.id.textViewDescription)

        with(selectedComicResultItem) {
            Glide.with(requireContext()).load(thumbnail.getThumbnailUrlComicBook())
                .into(imageViewComicBookDescription);

            textViewComicTitleDescription.text = title
            textViewComicYear.text = formatCreationDate(modified)
            textViewComicPages.text = pageCount.toString()
            textViewCreators.text = getCreatorsComicBook(creators)
            textViewDescription.text = description ?: "Sem Descrição"
        }

        return fragment
    }

    fun getCreatorsComicBook(creators: ComicCreators): String {
        return creators.items.joinToString(separator = ",") { it.name }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatCreationDate(date: String): String{
        var stringDate: String
        try {
            stringDate = LocalDate.parse(date.substring(0,10))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }catch (_: RuntimeException){
            stringDate = "Sem Data"
        }
        return stringDate
    }

    companion object {
        fun newInstance(comicResult: ComicResult): ComicBookDescriptionFragment {
            return ComicBookDescriptionFragment().apply {
                selectedComicResultItem = comicResult
            }
        }
    }
}