package com.frezzcoding.skincareadvisor.functionalities.home

import com.frezzcoding.skincareadvisor.common.api.ApiService
import com.frezzcoding.skincareadvisor.data.Curiosity
import javax.inject.Inject

class HomeRepository @Inject constructor(var apiService: ApiService) {

    suspend fun getCuriosities() : List<Curiosity> {
        val response = apiService.getCuriosities()
        if (response != null) {
            if(response.isSuccessful){
                return response.body()!!
            }
        }
        return arrayListOf(Curiosity(0, "", "", "false", ""))
    }


}