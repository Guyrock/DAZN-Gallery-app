package com.dazn.gallery.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dazn.gallery.models.ImgDetail
import com.dazn.gallery.utils.AppUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {

    private val _imagesFromJson = MutableLiveData<ArrayList<ImgDetail>>()
    val imagesFromJson: LiveData<ArrayList<ImgDetail>> = _imagesFromJson

    fun getDataFromJson(context:Context){
        viewModelScope.launch(Dispatchers.Default) {
            val jsonString = AppUtils().getDataFromJsonFile(context,"nasa_details.json")
            val gson = Gson()
            val listImages = object : TypeToken<ArrayList<ImgDetail>>(){}.type
            _imagesFromJson.postValue(gson.fromJson(jsonString,listImages))
        }
    }
}