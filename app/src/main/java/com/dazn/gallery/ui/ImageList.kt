package com.dazn.gallery.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dazn.gallery.R
import com.dazn.gallery.models.ImgDetail
import com.dazn.gallery.viewmodels.ImageViewModel
import kotlinx.android.synthetic.main.fragment_image_list.*

class ImageList : Fragment() {
    private val viewModel : ImageViewModel by activityViewModels()
    private lateinit var imgListAdapter : ImageListAdapter
    private lateinit var imgList: ArrayList<ImgDetail>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imgList = ArrayList()
        imgListAdapter = ImageListAdapter(requireContext(),imgList,object : ImageListAdapter.ImageClickListener{
            override fun showDetails(pos: Int) {
                val bundle = Bundle().apply {
                    putInt("pos",pos)
                }
                findNavController().navigate(
                    R.id.imageDetails,
                    bundle
                )
            }
        })
        viewModel.getDataFromJson(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryRV.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)
            adapter = imgListAdapter
        }
        viewModel.imagesFromJson.observe(viewLifecycleOwner){
            imgList.apply {
                clear()
                addAll(it)
                sortByDescending {
                    it.date
                }
            }
            imgListAdapter.notifyDataSetChanged()
        }
    }
}