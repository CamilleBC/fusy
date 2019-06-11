package com.camillebc.fusy.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FictionViewModel: ViewModel() {
    val fictionList =  MutableLiveData<MutableList<Fiction>>().apply { value = mutableListOf() }
    val fiction = MutableLiveData<Fiction>()
}