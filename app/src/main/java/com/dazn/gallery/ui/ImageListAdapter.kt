package com.dazn.gallery.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dazn.gallery.R
import com.dazn.gallery.models.ImgDetail

class ImageListAdapter(
    private val context:Context,
    private val images:ArrayList<ImgDetail>,
    private val listener:ImageClickListener
) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

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
            it.url?.let { imageToLoad ->
                Glide.with(context)
                    .load(imageToLoad.replace("http:","https:"))
                    .into(holder.imageView)
            } ?: kotlin.run {
                Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(holder.imageView)
            }
            holder.imageView.setOnClickListener { click ->
                listener.showDetails(position,it)
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val imageView : ImageView = view.findViewById(R.id.smallImg)
    }

    interface ImageClickListener{
        fun showDetails(pos:Int,imgDetail: ImgDetail)
    }
}