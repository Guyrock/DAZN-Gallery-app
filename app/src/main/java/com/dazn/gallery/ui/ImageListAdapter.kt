package com.dazn.gallery.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dazn.gallery.R
import com.dazn.gallery.models.ImgDetail

class ImageListAdapter(
    private val context:Context
) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    private val images : ArrayList<ImgDetail> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
            R.layout.list_item,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        images[position].also {
            it.url?.let { imageToLoad->
                Glide.with(context)
                    .load(imageToLoad.replace("http","https"))
                    .into(holder.imageView)
            } ?: kotlin.run {
                Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(holder.imageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun submitImages(imgs : List<ImgDetail>){
        images.clear()
        images.addAll(imgs)
    }

    class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val imageView : ImageView = view.findViewById(R.id.smallImg)
    }
}