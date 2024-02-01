package com.examenp.dillan.dispositivosm_recyclewie_dc.logic.usercase

import android.util.Log
import com.examenp.dillan.dispositivosm_recyclewie_dc.core.Constants
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.endpoints.TopAnimesEndPoint
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.entities.topanime.TopAnimes
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.repository.RetrofitBase


import java.lang.Exception

class JikanGetTopAnimesUserCase {

    suspend fun getResponce(): Result<TopAnimes> {

        var result: Result<TopAnimes>? = null;

        var infoAnime = TopAnimes()

        try {
            val baseService = RetrofitBase.getRetrofiJikamConnection()
            val service = baseService.create(TopAnimesEndPoint::class.java)
            val call = service.getAllTopAnimes()

            if (call.isSuccessful) {
                val a = call.body()!!
                infoAnime = a
                result = Result.success(infoAnime)
            } else {
                Log.e(Constants.TAG, "Error en el llamado de la Api de Jikan")
                result = Result.failure(Exception("Error en el llamado de la Api de Jikan"))
            }
        } catch (ex: Exception) {
            Log.e(Constants.TAG, ex.stackTraceToString())
            result = Result.failure(Exception(ex))
        }
        return result!!

    }

}