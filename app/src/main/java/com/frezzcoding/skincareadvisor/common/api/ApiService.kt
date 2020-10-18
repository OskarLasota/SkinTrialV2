package com.frezzcoding.skincareadvisor.common.api

import com.frezzcoding.skincareadvisor.data.Curiosity
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("curiosities.php")
    suspend fun getCuriosities() : Response<ArrayList<Curiosity>>


}