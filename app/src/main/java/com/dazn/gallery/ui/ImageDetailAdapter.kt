package com.dazn.gallery.ui

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dazn.gallery.R
import com.dazn.gallery.models.ImgDetail

class ImageDetailAdapter(
    private val context: Context,
    private val images:ArrayList<ImgDetail>
) : RecyclerView.Adapter<ImageDetailAdapter.DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.detail_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        images[position].also {
            it.hdurl?.let { imageToLoad ->
                Glide.with(context)
                    .load(imageToLoad.replace("http:","https:"))
                    .into(holder.hdImg)
            } ?: kotlin.run {
                Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(holder.hdImg)
            }
            holder.title.text = it.title
            holder.desc.apply {
                text = it.explanation
                movementMethod = ScrollingMovementMethod()
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val hdImg : ImageView = view.findViewById(R.id.hdImg)
        val title : TextView = view.findViewById(R.id.title)
        val desc : TextView = view.findViewById(R.id.desc)
    }
}