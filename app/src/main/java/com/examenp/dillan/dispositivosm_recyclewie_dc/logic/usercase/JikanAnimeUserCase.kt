package com.examenp.dillan.dispositivosm_recyclewie_dc.logic.usercase

import android.util.Log
import com.examenp.dillan.dispositivosm_recyclewie_dc.core.Constants
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.endpoints.AnimeEndPoint
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.endpoints.getFullInfoAnimeLG
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.repository.RetrofitBase
import com.examenp.dillan.dispositivosm_recyclewie_dc.logic.entities.FullInfoAnimeLG

import java.lang.Exception

class JikanAnimeUserCase {

    suspend fun getFullAnimeInfo(nameAnime:Int): Result<FullInfoAnimeLG> {

        var result:Result<FullInfoAnimeLG>?=null
        var infoAnime: FullInfoAnimeLG = FullInfoAnimeLG()
        Log.d(Constants.TAG, "antes de try: ")
        try {
            val baseService= RetrofitBase.getRetrofiJikamConnection()
            Log.d(Constants.TAG, "baseService")
            val service= baseService.create(AnimeEndPoint::class.java) //creo mi servicio
            Log.d(Constants.TAG, "service")
            val call= service.getAnimeFullInformation(nameAnime) //ahora si podria acceder a travez de servicio a los metodos
            //me revuelve un response de FullInfoAnime
            Log.d(Constants.TAG, "call")

            Log.d(Constants.TAG, "antes de call.isSu..: "+call)
            if(call.isSuccessful){
                Log.d(Constants.TAG, "call: "+call)
                val a=call.body()!!
                infoAnime=a.getFullInfoAnimeLG()


                result = Result.success(infoAnime)
                Log.d(Constants.TAG, "result: "+result)
            }else{
                Log.d(Constants.TAG, "Error en el llamado a la API de Jikan")
                result=Result.failure(Exception("Error en el llamado a la API de Jikan"))
            }
        }catch (ex:Exception){
            Log.e(Constants.TAG,"catch: " +ex.stackTraceToString())
            result=Result.failure(Exception(ex))
        };
        return result!!
    }



}

