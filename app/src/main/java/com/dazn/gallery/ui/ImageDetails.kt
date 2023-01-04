package com.dazn.gallery.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.dazn.gallery.R
import com.dazn.gallery.models.ImgDetail
import com.dazn.gallery.viewmodels.ImageViewModel
import kotlinx.android.synthetic.main.fragment_image_details.*

private const val ARG_PARAM1 = "pos"

class ImageDetails : Fragment() {
    private var pos: Int = 0
    private val viewModel : ImageViewModel by activityViewModels()
    private lateinit var imageDetailAdapter: ImageDetailAdapter
    private lateinit var imgList: ArrayList<ImgDetail>
    private lateinit var snapHelper : SnapHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pos = it.getInt(ARG_PARAM1)
        }
        imgList = ArrayList()
        imageDetailAdapter = ImageDetailAdapter(requireContext(),imgList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsRv.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = imageDetailAdapter
        }
        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(detailsRv)
        viewModel.imagesFromJson.observe(viewLifecycleOwner){
            imgList.apply {
                addAll(it)
                sortByDescending {
                    it.date
                }
            }
            imageDetailAdapter.notifyDataSetChanged()
            detailsRv.scrollToPosition(pos)
        }
    }
}