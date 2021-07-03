package com.frezzcoding.skincareadvisor.functionalities.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frezzcoding.skincareadvisor.data.Curiosity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _curiosities = MutableLiveData<ArrayList<Curiosity>>()
    val curiosities: LiveData<ArrayList<Curiosity>> = _curiosities

    private val mOperation = MutableLiveData<Boolean>()
    val operation: LiveData<Boolean> = mOperation


    fun getCuriosities() {
        CoroutineScope(Dispatchers.IO).launch {
            _curiosities.postValue(repository.getCuriosities() as ArrayList<Curiosity>?)
        }
    }


}