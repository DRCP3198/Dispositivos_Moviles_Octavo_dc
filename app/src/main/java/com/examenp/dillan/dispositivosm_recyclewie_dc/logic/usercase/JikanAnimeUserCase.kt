package com.examenp.dillan.dispositivosm_recyclewie_dc.logic.usercase

import android.util.Log
import com.examenp.dillan.dispositivosm_recyclewie_dc.core.Constants
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.endpoints.AnimeEndPoint
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.endpoints.getFullInfoAnimeLG
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.repository.RetrofitBase
import com.examenp.dillan.dispositivosm_recyclewie_dc.logic.entities.FullInfoAnimeLG

import java.lang.Exception

class JikanAnimeUserCase {

    fun getFullAnimeInformation(nameAnime: Int): FullInfoAnimeLG {

        var infoAnime: FullInfoAnimeLG = FullInfoAnimeLG()
       try {
           val baseService = RetrofitBase.getRetrofiJikamConnection()
           val service = baseService.create(AnimeEndPoint::class.java)
           val call = service.getAnimeFullInformation(nameAnime)


           if (call.isSuccessful) {
               val a = call.body()!!
               infoAnime = a.getFullInfoAnimeLG()
           } else {
               Log.d(Constants.TAG, "Error en el llamado de la Api de Jikan")
           }
       } catch (ex:Exception){
           Log.e(Constants.TAG,"")
       }
        return infoAnime

    }

    }

