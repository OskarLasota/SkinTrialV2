package com.frezzcoding.skincareadvisor.functionalities.home

import com.frezzcoding.skincareadvisor.common.api.ApiService
import com.frezzcoding.skincareadvisor.data.Curiosity
import javax.inject.Inject

class HomeRepository @Inject constructor(var apiService: ApiService) {

    suspend fun getCuriosities() : List<Curiosity> {
        val response = apiService.getCuriosities()
            if(response.isSuccessful){
                return response.body()!!
            }
        //this needs to be changed, not safe
        return arrayListOf(Curiosity(0, "", "", "false", ""))
    }


}