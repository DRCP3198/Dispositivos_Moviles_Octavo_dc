package com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {

    //Acceso a la base de datos
    private const val JIKAN_URL= ""


    fun getRetrofiJikamConnection():Retrofit{

        return Retrofit.Builder().baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create()) //Esta línea me permite serializar mis datos ;
            .build()
    }


}