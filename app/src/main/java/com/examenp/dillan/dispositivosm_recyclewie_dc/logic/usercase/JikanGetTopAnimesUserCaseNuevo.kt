package com.examenp.dillan.dispositivosm_recyclewie_dc.logic.usercase

import android.util.Log
import com.examenp.dillan.dispositivosm_recyclewie_dc.core.Constants
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.endpoints.TopAnimesEndPoint
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.endpoints.getFullInfoAnimeLG
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.entities.topanime.TopAnimes
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.repository.RetrofitBase
import com.examenp.dillan.dispositivosm_recyclewie_dc.logic.entities.FullInfoAnimeLG


import java.lang.Exception

class JikanGetTopAnimesUserCaseNuevo {

    suspend fun invoke(): Result<List<FullInfoAnimeLG>> {

        var result: Result<List<FullInfoAnimeLG>>? = null;
        val items = ArrayList<FullInfoAnimeLG>()

        try {
            val baseService = RetrofitBase.getRetrofiJikamConnection()
            val service = baseService.create(TopAnimesEndPoint::class.java)
            val call = service.getAllTopAnimes()

            if (call.isSuccessful) {
                val infoAnime = call.body()!!
                infoAnime.data.forEach {
                    items.add(it.getFullInfoAnimeLG())
                }
                result = Result.success(items)
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