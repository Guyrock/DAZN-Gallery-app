package com.dazn.gallery.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dazn.gallery.models.ImgDetail
import com.dazn.gallery.utils.AppUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImageViewModel : ViewModel() {

    private val _imagesFromJson = MutableLiveData<ArrayList<ImgDetail>>()
    val imagesFromJson = _imagesFromJson

    fun getDataFromJson(context:Context){
        val jsonString = AppUtils().getDataFromJsonFile(context,"nasa_details.json")
        val gson = Gson()
        val listImages = object : TypeToken<ArrayList<ImgDetail>>(){}.type
        _imagesFromJson.value = gson.fromJson(jsonString,listImages)
    }
}